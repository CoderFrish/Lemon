package me.coderfrish.plugin;

import me.coderfrish.plugin.config.Config;
import me.coderfrish.plugin.config.ConfigManager;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PluginConfig {
    private final Plugin plugin;

    public PluginConfig(Plugin plugin) {
        this.plugin = plugin;
    }

    public void register(String name, String config) {
        File dataFolder = JavaPluginManager.getPlugin(plugin).getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File configFile =  new File(dataFolder, name + ".yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                Files.writeString(configFile.toPath(), config);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        YamlConfiguration configData = YamlConfiguration.loadConfiguration(configFile);
        ConfigManager.addConfig(name, new Config(configData));
    }

    public Config getConfig(String name) {
        return ConfigManager.getConfig(name);
    }
}
