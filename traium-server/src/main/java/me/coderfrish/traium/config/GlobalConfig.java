package me.coderfrish.traium.config;

import java.io.File;

import static me.coderfrish.traium.utils.Constants.CONFIG_FOLDER;

public class GlobalConfig extends TraiumConfig {
    private static final File CONFIG_GLOBAL_FILE = new File(CONFIG_FOLDER, "traium_global.toml");

    public GlobalConfig() {
        super(CONFIG_GLOBAL_FILE);
    }

    @ConfigField(type = ConfigTypes.globals, parent = "i18n", comments = {
            "Please use the key from https://minecraft.wiki/w/Language",
            "Format example: en_us zh_cn"
    })
    public static String language = "en_us";

    @ConfigField(name = "enabled", type = ConfigTypes.fixed, parent = "unsafe_teleportation", comments = {
            "If you want to use sand duping,please turn on this.",
            "Warning: This would cause some unsafe issues, you could learn more on : https://github.com/PaperMC/Folia/issues/297."
    })
    public static boolean enableUnsafeTeleportation = true;

    @ConfigField(type = ConfigTypes.misc, parent = "server_brand_name", comments = {
            "Server brand name displayed to clients."
    })
    public static String serverBrandName = "Traium";

    @ConfigField(type = ConfigTypes.misc, parent = "ender_chest_six_rows", comments = {
            "The amount of rows a barrel should have. Min: 1, Max: 6"
    })
    public static int barrelRows = 3;

    @ConfigField(name = "enabled", type = ConfigTypes.misc, parent = "ender_chest_six_rows", comments = {
            "When enabled, ender chests should have six rows of inventory space."
    })
    public static boolean enableEnderChestSixRows = false;

    @ConfigField(type = ConfigTypes.misc, parent = "ender_chest_six_rows", comments = {
            "Use permission nodes to determine the number of rows. By default, with this setting enabled, all players have rows unless otherwise specified using permissions"
    })
    public static boolean enderChestPermissionRows = false;
}
