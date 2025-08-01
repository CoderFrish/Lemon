package me.coderfrish.server.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventMap {
    private final static Map<String, Class<? extends Event>> map;

    static {
        map = new ConcurrentHashMap<>() {};
    }

    public static Map<String, Class<? extends Event>> getMap() {
        return map;
    }
}
