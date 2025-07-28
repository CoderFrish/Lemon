package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(name = "enabled_folia_supported",
        comment = "This config is order to configure enabled field of `folia-supported` in plugin.yml or paper-plugin.yml.",
        type = ConfigCategory.experiment
)
public class FoliaSupportedConfig {
    @ConfigField
    public static boolean enabled = true;
}
