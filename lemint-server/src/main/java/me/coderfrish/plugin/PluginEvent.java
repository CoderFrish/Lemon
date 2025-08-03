package me.coderfrish.plugin;

import me.coderfrish.event.EventMap;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class PluginEvent {
    public final LuaFunction listen;

    public PluginEvent(Plugin plugin) {
        this.listen = new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue arg1, LuaValue arg2) {
                Class<? extends Event> eventClass = EventMap.getEvents().get(arg1.tojstring());
                if (eventClass == null) {
                    throw new RuntimeException("Event - " + arg1.tojstring() + " is not found.");
                }

                Bukkit.getPluginManager().registerEvent(
                        eventClass,
                        new Listener() {},
                        EventPriority.NORMAL,
                        (listener, event) -> {
                            arg2.call(CoerceJavaToLua.coerce(event));
                        },
                        JavaPluginManager.getPlugin(plugin)
                );
                return NIL;
            }
        };
    }
}
