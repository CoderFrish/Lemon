package me.coderfrish.plugin;

import me.coderfrish.plugin.api.JavaPlugin;
import me.coderfrish.plugin.api.ScriptPlugin;
import me.coderfrish.plugin.api.ScriptPluginMeta;
import me.coderfrish.plugin.exception.InvalidScriptException;
import me.coderfrish.plugin.pack.PluginPack;
import org.bukkit.plugin.PluginBase;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.io.IOAccess;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ScriptPluginManager {
    private static final List<ScriptPlugin> plugins = new CopyOnWriteArrayList<>();
    private static final Map<ScriptPlugin, Value> installers = new ConcurrentHashMap<>();
    private static final Set<Context> contexts = new CopyOnWriteArraySet<>();
    private static final Map<ScriptPlugin, PluginBase> javaPlugins =  new ConcurrentHashMap<>();

    public static void loadPlugins(File pluginFolder) {
        for (File file : pluginFolder.listFiles()) {
            if (file.getName().endsWith(".js") || file.getName().endsWith(".mjs")) {
                loadSinglePlugin(file);
            }

            if (file.getName().endsWith(".pack")) {
                ScriptUnpackManager.register(file);
            }
        }

        loadPackPlugins();
        ScriptUnpackManager.save();
    }

    private static void loadPackPlugins() {
        List<PluginPack> packs = ScriptPackManager.getPacks();
        packs.forEach(pluginPack -> {
            File dataFolder = new File(ScriptUnpackManager.dataFolder, pluginPack.getSha());
            File main = new File(dataFolder, pluginPack.getMain());

            Context context = Context.newBuilder()
                    .logHandler(PrintStream.nullOutputStream())
                    .allowHostAccess(HostAccess.ALL)
                    .allowHostClassLookup(className -> true)
                    .option("js.esm-eval-returns-exports", "true")
                    .allowIO(IOAccess.ALL)
                    .allowNativeAccess(false)
                    .build();

            try {
                newPluginInstance(main, context);
            } catch (IOException e) {
                throw new InvalidScriptException(e);
            }
        });
    }

    private static void loadSinglePlugin(File file) {
        try {
            Context context = Context.newBuilder()
                    .logHandler(PrintStream.nullOutputStream())
                    .allowHostAccess(HostAccess.ALL)
                    .allowHostClassLookup(className -> true)
                    .option("js.esm-eval-returns-exports", "true")
                    .allowNativeAccess(false)
                    .build();

            newPluginInstance(file, context);
        } catch (IOException e) {
            throw new InvalidScriptException(e);
        }
    }

    private static void newPluginInstance(File file, Context context) throws IOException {
        Source source = Source.newBuilder("js", file)
                .mimeType("application/javascript+module").build();
        Value module = context.eval(source);
        Value plugin = module.getMember("default");
        ScriptPluginMeta meta = conversionToMeta(plugin);
        ScriptPlugin scriptPlugin = new ScriptPlugin(meta);
        Value installer = plugin.getMember("installer").execute(scriptPlugin);

        plugins.add(scriptPlugin);
        installers.put(scriptPlugin, installer);
        contexts.add(context);
    }

    public static void onLoad() {
        installers.forEach((plugin, installer) -> {
            javaPlugins.put(plugin, new JavaPlugin(plugin));
            Value onLoad = installer.getMember("onLoaded");
            if (onLoad != null) {
                onLoad.execute();
            }
        });
    }

    public static void onEnabled() {
        installers.forEach((plugin, ignore) -> {
            plugin.setEnable(true);
        });
    }

    public static void onDisabled() {
        installers.forEach((plugin, ignore) -> {
            plugin.setEnable(false);
        });

        contexts.forEach(Context::close);
        contexts.clear();
    }

    public static void enablePlugin(ScriptPlugin plugin) {
        Value installer = installers.get(plugin);
        if (!plugin.isEnable()) {
            Value onEnabled = installer.getMember("onEnabled");
            if (onEnabled != null) {
                onEnabled.execute();
            }
        }
    }

    public static void disablePlugin(ScriptPlugin plugin) {
        Value installer = installers.get(plugin);
        if (plugin.isEnable()) {
            Value onDisabled = installer.getMember("onDisabled");
            if (onDisabled != null) {
                onDisabled.execute();
            }
        }
    }

    private static ScriptPluginMeta conversionToMeta(Value value) {
        // name
        Value name = value.getMember("name");
        if (name == null) {
            throw new InvalidScriptException("Missing plugin name");
        }

        String jName = name.asString();
        if (!jName.matches("^[A-Za-z0-9]+$")) {
            throw new InvalidScriptException("Plugin name is invalid");
        }

        // version
        Value version = value.getMember("version");
        if (version == null) {
            throw new InvalidScriptException("Missing plugin version");
        }

        String jVersion = version.asString();

        // description
        Value description = value.getMember("description");
        String jDescription;
        if (description == null) {
            jDescription = "";
        } else {
            jDescription = description.asString();
        }

        // installer
        Value installer = value.getMember("installer");
        if (installer == null) {
            throw new InvalidScriptException("Missing plugin installer");
        }

        return new ScriptPluginMeta(jName, jVersion, jDescription, installer);
    }

    public static List<ScriptPlugin> getPlugins() {
        return plugins;
    }

    public static Map<ScriptPlugin, PluginBase> getJavaPlugins() {
        return javaPlugins;
    }
}
