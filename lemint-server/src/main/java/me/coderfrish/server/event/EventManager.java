package me.coderfrish.server.event;

import org.bukkit.plugin.InvalidPluginException;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager implements me.coderfrish.plugin.EventManager {
    private final Map<Class<? extends Event>, CopyOnWriteArrayList<LuaFunction>> events = new HashMap<>();

    public void register(LuaValue name, LuaFunction listener) {
        Class<? extends Event> aClass = EventMap.getMap().get(name.tojstring());

        if (aClass == null) {
            try {
                throw new InvalidPluginException("Event: " + name.tojstring() + "not found.");
            } catch (InvalidPluginException e) {
                throw new RuntimeException(e);
            }
        }

        if (!events.containsKey(aClass)) {
            events.put(aClass, new CopyOnWriteArrayList<>());
        }

        events.get(aClass).add(listener);
    }

    public void unregister() {
        events.clear();
    }

    public CopyOnWriteArrayList<LuaFunction> getEvent(Class<? extends Event> event) {
        return events.getOrDefault(event, new CopyOnWriteArrayList<>());
    }
}
