package me.coderfrish.plugin.api;

import me.coderfrish.plugin.ScriptPluginManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginBase;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyExecutable;

public class ScriptEvent {
    private final ScriptPlugin plugin;

    public ScriptEvent(ScriptPlugin plugin) {
        this.plugin = plugin;
    }

    public void listen(Class<? extends Event> event, ProxyExecutable listener) {
        Bukkit.getPluginManager().registerEvent(event,
                new Listener() {},
                EventPriority.NORMAL,
                (listener0, event0) -> {
                    listener.execute(Value.asValue(event0));
                }, getBukkitJavaPlugin()
        );
    }

    private PluginBase getBukkitJavaPlugin() {
        return ScriptPluginManager.getJavaPlugins().get(plugin);
    }
}
