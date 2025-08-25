package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(type = ConfigCategory.experiment, name = "bukkit_entity_api")
public class BukkitEntityApiConfig {
    @ConfigField
    public static boolean teleport = false;
}
