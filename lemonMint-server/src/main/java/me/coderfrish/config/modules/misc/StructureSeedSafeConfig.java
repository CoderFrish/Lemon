package me.coderfrish.config.modules.misc;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(name = "structure_seed_safe", type = ConfigCategory.misc)
public class StructureSeedSafeConfig {
    @ConfigField
    public static boolean enabled = false;
}
