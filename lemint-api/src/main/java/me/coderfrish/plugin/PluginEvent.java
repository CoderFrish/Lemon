package me.coderfrish.plugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class PluginEvent {
    public final Plugin plugin;

    public PluginEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    public void listen(Class<? extends Event> eventClazz, LuaFunction listenerFunction) {
        Bukkit.getPluginManager().registerEvent(eventClazz,
                new Listener() {},
                EventPriority.NORMAL,
                (listener, event) -> {
                    listenerFunction.call(CoerceJavaToLua.coerce(event));
                },
                JavaPluginManager.getPlugin(plugin)
        );
    }
}
