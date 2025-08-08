package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(type = ConfigCategory.experiment, name = "bukkit_scheduler")
public class BukkitSchedulerConfig {
    @ConfigField
    public static boolean enabled = false;
}
