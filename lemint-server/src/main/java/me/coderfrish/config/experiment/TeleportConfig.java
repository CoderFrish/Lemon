package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(name = "fixed_teleport", type = ConfigCategory.experiment)
public class TeleportConfig {
    @ConfigField
    public static boolean enabled = false;
}
