package me.coderfrish.config.misc;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.ConfigField;
import dev.bacteriawa.mint.config.Configuration;

//@Configuration(type = ConfigCategory.misc, name = "ender_chest_six_rows")
public class EnderChestSixRowsConfig {
    @ConfigField
    public static boolean enderChestSixRows = false;

    @ConfigField
    public static boolean enderChestPermissionRows = false;

    @ConfigField
    public static int barrelRows = 3;
}
