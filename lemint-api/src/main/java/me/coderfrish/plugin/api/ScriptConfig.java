package me.coderfrish.plugin.api;

import me.coderfrish.plugin.ScriptPluginManager;
import me.coderfrish.plugin.exception.InvalidScriptException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScriptConfig {
    private final Map<String, YamlConfiguration> configs = new ConcurrentHashMap<>();
    private final ScriptPlugin plugin;

    public ScriptConfig(ScriptPlugin plugin) {
        this.plugin = plugin;
    }

    public void load(String configName, String defaultConfig) {
        YamlConfiguration config = new YamlConfiguration();
        if (!getBukkitJavaPlugin().getDataFolder().exists()) {
            getBukkitJavaPlugin().getDataFolder().mkdir();
        }

        File configFile = new File(getBukkitJavaPlugin().getDataFolder(), configName + ".yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                Files.writeString(configFile.toPath(), defaultConfig, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new InvalidScriptException(e);
            }
        }

        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            throw new InvalidScriptException(e);
        }
        configs.put(configName, config);
    }

    public Object get(String configName, String key) {
        return configs.get(configName).get(key);
    }

    private PluginBase getBukkitJavaPlugin() {
        return ScriptPluginManager.getJavaPlugins().get(plugin);
    }
}
