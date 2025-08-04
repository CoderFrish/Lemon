package me.coderfrish.plugin.config;

import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
    private final YamlConfiguration config;

    public Config(YamlConfiguration config) {
        this.config = config;
    }

    public Object get(String key) {
        return config.get(key);
    }
}
