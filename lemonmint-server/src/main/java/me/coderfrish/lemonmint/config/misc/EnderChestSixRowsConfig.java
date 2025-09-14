package me.coderfrish.lemonmint.config.misc;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.annotation.Config;
import dev.bacteriawa.mint.config.annotation.ConfigField;

@Config(category = ConfigCategory.misc, name = "ender_chest_six_rows")
public class EnderChestSixRowsConfig {
    @ConfigField
    public static boolean enderChestSixRows = false;

    @ConfigField
    public static boolean enderChestPermissionRows = false;

    @ConfigField
    public static int barrelRows = 3;
}
