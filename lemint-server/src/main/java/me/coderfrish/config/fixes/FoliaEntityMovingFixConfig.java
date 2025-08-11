package me.coderfrish.config.fixes;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(name = "folia_entity_moving_fix", type = ConfigCategory.fixes)
public class FoliaEntityMovingFixConfig {
    public static boolean enable = false;

    public static boolean warnOnDetected = true;
}
