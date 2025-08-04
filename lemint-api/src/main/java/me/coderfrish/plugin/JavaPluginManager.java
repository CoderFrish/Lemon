package me.coderfrish.plugin;

import org.bukkit.plugin.PluginBase;
import java.util.HashMap;

public class JavaPluginManager {
    private static final HashMap<Plugin, PluginBase> plugins = new HashMap<>();

    public static void addPlugin(Plugin plugin) {
        plugins.put(plugin, new JavaPlugin(plugin));
    }

    public static void removePlugin(Plugin plugin) {
        plugins.remove(plugin);
    }

    public static PluginBase getPlugin(Plugin plugin) {
        return plugins.get(plugin);
    }

    public static HashMap<Plugin, PluginBase> getPlugins() {
        return plugins;
    }
}
