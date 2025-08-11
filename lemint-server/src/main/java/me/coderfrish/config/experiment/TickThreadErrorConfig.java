package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(type = ConfigCategory.experiment, name = "tick_thread_error")
public class TickThreadErrorConfig {
    @ConfigField
    public static boolean enable = true;
}
