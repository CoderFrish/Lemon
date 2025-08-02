package me.coderfrish.event;

import org.bukkit.event.Event;
import org.bukkit.event.player.*;
import org.bukkit.event.server.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventMap {
    private static final Map<String, Class<? extends Event>> events;

    static {
        events = new ConcurrentHashMap<>() {
            {
                put("PlayerJoinEvent", PlayerJoinEvent.class);
                put("PlayerQuitEvent", PlayerQuitEvent.class);
                put("BroadcastMessageEvent", BroadcastMessageEvent.class);
                put("ServerLoadEvent", ServerLoadEvent.class);
                put("ServerListPingEvent", ServerListPingEvent.class);
                put("RemoteServerCommandEvent", RemoteServerCommandEvent.class);
                put("ServerCommandEvent", ServerCommandEvent.class);
            }
        };
    }

    public static Map<String, Class<? extends Event>> getEvents() {
        return events;
    }
}
