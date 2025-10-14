package me.coderfrish.traium.config;

import java.io.File;

import static me.coderfrish.traium.utils.Constants.CONFIG_FOLDER;

public class GlobalConfig extends TraiumConfig {
    private static final File CONFIG_GLOBAL_FILE = new File(CONFIG_FOLDER, "traium_global.toml");

    public GlobalConfig() {
        super(CONFIG_GLOBAL_FILE);
    }

    @ConfigField(type = ConfigTypes.misc, parent = "pufferfish_sentry", name = "sentry_dsn", comments = {
            "Sentry DSN for improved error logging, leave blank to disable,",
            "Obtain from https://sentry.io/"
    })
    public static String sentryDsn = "";

    @ConfigField(type = ConfigTypes.misc, parent = "pufferfish_sentry", name = "log_level",
            comments = "Logs with a level higher than or equal to this level will be recorded.")
    public static String logLevel = "WARN";

    @ConfigField(type = ConfigTypes.misc, parent = "pufferfish_sentry", name = "only_log_thrown",
            comments = "Only log with a Throwable will be recorded after enabling this.")
    public static boolean onlyLogThrown = true;

    @ConfigField(type = ConfigTypes.globals, parent = "i18n", comments = {
            "Please use the key from https://minecraft.wiki/w/Language",
            "Format example: en_us zh_cn"
    })
    public static String language = "en_us";

    @ConfigField(type = ConfigTypes.misc, parent = "server_brand_name", comments = {
            "Server brand name displayed to clients."
    }, name = "value")
    public static String serverBrandName = "Traium";
}
