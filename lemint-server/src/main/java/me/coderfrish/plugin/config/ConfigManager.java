package me.coderfrish.plugin.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigManager {
    private static final Map<String, Config> configs = new ConcurrentHashMap<>();

    public static void addConfig(String name, Config config) {
        configs.put(name, config);
    }

    public static Config getConfig(String name) {
        if (!configs.containsKey(name)) {
            throw new RuntimeException("Config " + name + " does not exist!");
        }

        return configs.get(name);
    }
}
