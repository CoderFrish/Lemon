package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(name = "enabled_scoreboard", type = ConfigCategory.experiment)
public class ScoreboardConfig {
    @ConfigField
    public static boolean enabled = false;
}
