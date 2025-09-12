package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.annotation.Config;
import dev.bacteriawa.mint.config.annotation.ConfigField;

/**
 * @deprecated 暂时弃用，未来会恢复使用的，就算设置了也没啥用 ()
 */
@Deprecated
@Config(name = "legacy_bukkit_plugin", category = ConfigCategory.experiment)
public class LegacyBukkitPluginConfig {
    @ConfigField
    public static boolean foliaSupportedField = false;

    @ConfigField
    public static boolean scheduler = true;

    @ConfigField
    public static boolean transformer = true;
}
