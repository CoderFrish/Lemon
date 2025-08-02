package me.coderfrish.server.plugin;

import me.coderfrish.plugin.Plugin;
import me.coderfrish.server.event.EventManager;
import org.luaj.vm2.*;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JseBaseLib;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PluginManager implements me.coderfrish.plugin.PluginManager {
    private final me.coderfrish.plugin.PluginManager pluginManager = this;
    public static final EventManager eventManager = new EventManager();
    public static final List<Plugin> plugins = new CopyOnWriteArrayList<>();

    public void loadPlugins(Path path) {
        for (File file : path.toFile().listFiles()) {
            if (file.getName().endsWith(".lua")) {
                loadSingleFilePlugin(file);
            }
        }

        initPlugins();
    }

    private void loadSingleFilePlugin(File plugin) {
        Globals env = this.setupLuaEnvironment();
        LuaValue chunk = env.loadfile(plugin.getAbsolutePath());

        chunk.call();
    }

    public void initPlugins() {
        for (Plugin plugin : plugins) {
            plugin.init.call();
        }
    }

    public void enablePlugins() {
        for (Plugin plugin : plugins) {
            plugin.enable.call();
        }
    }

    public void disablePlugins() {
        for (Plugin plugin : plugins) {
            plugin.disable.call();
        }
        eventManager.unregister();
    }

    private Globals setupLuaEnvironment() {
        Globals globals = new Globals();
        globals.load(new JseBaseLib());
        LoadState.install(globals);
        LuaC.install(globals);

        /* setup plugin manager function */
        globals.set("pluginManager", CoerceJavaToLua.coerce(pluginManager));

        return globals;
    }

    @Override
    public void register(LuaFunction plugin, LuaTable meta) {
        Plugin newPlugin = new Plugin(meta);

        plugin.call(CoerceJavaToLua.coerce(newPlugin));
        plugins.add(newPlugin);
    }
}
