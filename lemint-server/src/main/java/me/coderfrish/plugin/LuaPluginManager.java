package me.coderfrish.plugin;

import me.coderfrish.plugin.scheduler.*;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.InvalidDescriptionException;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LuaPluginManager implements PluginManager {
    private static final String VALID_NAME = "^[a-z0-9_]+$";
    private static final PluginManager pluginManager = new LuaPluginManager();
    private static final List<Plugin> plugins = new CopyOnWriteArrayList<>();

    private static final GlobalRegionScheduler globalRegionScheduler = new LuaGlobalRegionScheduler();
    private static final AsyncScheduler asyncScheduler = new LuaAsyncScheduler();
    private static final RegionScheduler regionScheduler = new LuaRegionScheduler();

    public static Path pluginFolder;

    public static void loadPlugins(Path path) {
        pluginFolder = path;
        for (File file : path.toFile().listFiles()) {
            if (file.getName().endsWith(".lua")) {
                loadPlugins(file);
            }
        }
    }

    private static void loadPlugins(File file) {
        Globals globals = setupPluginsGlobals();
        LuaValue chunk = globals.loadfile(file.getAbsolutePath());

        chunk.call();
    }

    private static Globals setupPluginsGlobals() {
        Globals globals = JsePlatform.standardGlobals();
        globals.set("pluginManager", CoerceJavaToLua.coerce(pluginManager));

        return globals;
    }

    public static void loadPlugins() {
        for (Plugin plugin : getPlugins()) {
            plugin.load.call();
        }
    }

    public static void enablePlugins() {
        for (Plugin plugin : getPlugins()) {
            plugin.enable.call();
        }
    }

    public static void disablePlugins() {
        for (Plugin plugin : getPlugins()) {
            plugin.disable.call();
            JavaPluginManager.removePlugin(plugin);
        }
    }

    @Override
    public void register(LuaFunction plugin, LuaTable table) {
        Plugin javaPlugin = new Plugin(coerceTableToPluginMeta(table));

        plugin.call(CoerceJavaToLua.coerce(javaPlugin));
        plugins.add(javaPlugin);
    }

    @Override
    public GlobalRegionScheduler globalRegionScheduler() {
        return globalRegionScheduler;
    }

    @Override
    public AsyncScheduler asyncScheduler() {
        return asyncScheduler;
    }

    @Override
    public RegionScheduler regionScheduler() {
        return regionScheduler;
    }

    @Override
    public EntityScheduler entityScheduler(Entity entity) {
        return new LuaEntityScheduler(entity);
    }

    public static List<Plugin> getPlugins() {
        return plugins;
    }

    private PluginMeta coerceTableToPluginMeta(LuaTable table) {
        LuaValue name = table.get("name");
        if (name == LuaValue.NIL)
            try {
                throw new InvalidDescriptionException("name is not defined");
            } catch (InvalidDescriptionException e) {
                throw new RuntimeException(e);
            }

        if (!name.tojstring().matches(VALID_NAME))
            try {
                throw new InvalidDescriptionException("name '" + name + "' contains invalid characters.");
            } catch (InvalidDescriptionException e) {
                throw new RuntimeException(e);
            }

        LuaValue description =  table.get("description");
        if (description == LuaValue.NIL)
            description = LuaValue.valueOf("");

        LuaValue version = table.get("version");
        if (version == LuaValue.NIL)
            try {
                throw new InvalidDescriptionException("version is not defined");
            } catch (InvalidDescriptionException e) {
                throw new RuntimeException(e);
            }

        LuaValue website = table.get("website");
        if (website == LuaValue.NIL)
            website = LuaValue.valueOf("");

        LuaValue license = table.get("license");
        if (license == LuaValue.NIL)
            license = LuaValue.valueOf("");

        LuaValue authors = table.get("authors");
        if (authors == LuaValue.NIL)
            authors = CoerceJavaToLua.coerce(new String[0]);

        LuaValue contributors = table.get("contributors");
        if (contributors == LuaValue.NIL)
            contributors = CoerceJavaToLua.coerce(new String[0]);

        return new PluginMeta(
                name.tojstring(),
                version.tojstring(),
                description.tojstring(),
                (String[]) CoerceLuaToJava.coerce(authors, String[].class),
                (String[]) CoerceLuaToJava.coerce(contributors, String[].class),
                website.tojstring(),
                license.tojstring()
        );
    }
}
