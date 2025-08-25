package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(name = "legacy_bukkit_plugin", type = ConfigCategory.experiment)
public class LegacyBukkitPluginConfig {
    @ConfigField
    public static boolean foliaSupportedField = false;

    @ConfigField
    public static boolean scheduler = true;

    @ConfigField
    public static boolean transformer = true;
}
