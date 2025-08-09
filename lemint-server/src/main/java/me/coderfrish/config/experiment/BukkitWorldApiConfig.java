package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(name = "bukkit_world_api", type = ConfigCategory.experiment)
public class BukkitWorldApiConfig {
    @ConfigField
    public static boolean creator = false;
}
