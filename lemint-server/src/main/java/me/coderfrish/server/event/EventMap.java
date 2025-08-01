package me.coderfrish.server.event;

import me.coderfrish.events.player.PlayerJoinEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventMap {
    private final static Map<String, Class<? extends Event>> map;

    static {
        map = new ConcurrentHashMap<>() {
            {
                put("player_join", PlayerJoinEvent.class);
            }
        };
    }

    public static Map<String, Class<? extends Event>> getMap() {
        return map;
    }
}
