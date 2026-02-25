package dev.coderfrish.lirael.config;

import org.apache.logging.log4j.Level;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LiraelConfig {
    private static final YamlConfiguration configuration = new YamlConfiguration();
    private static final Path CONFIG_FILE = Path.of("lirael-config.yml");
    private static final List<String> CONFIG_HEADERS = List.of(
            "Lirael is a Folia Fork maintained  by Lirael Project",
            "About this config file please to see public/document in open source.",
            "GitHub: https://www.github.com/CoderFrish/Lirael"
    );
    private static final int CONFIG_VERSION = 1;
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final Logger logger = LoggerFactory.getLogger("Lirael");

    public static void load() throws Exception {
        if (!Files.exists(CONFIG_FILE)) return;

        /* 加载逻辑 */
        Reader reader = Files.newBufferedReader(CONFIG_FILE, UTF8);
        configuration.load(reader);
        loadConfigs();
    }

    public static void save() throws Exception {
        if (Files.exists(CONFIG_FILE)) return;

        /* 保存逻辑 */
        configuration.options().setHeader(CONFIG_HEADERS);
        configuration.set("_version", CONFIG_VERSION);
        saveConfigs();
        Files.createFile(CONFIG_FILE);
        configuration.save(CONFIG_FILE.toFile());
    }

    // ======================================= configs =======================================

    private static String server_mod_name = "Lirael";

    private static String sentry_dsn = "";

    private static String sentry_log_level = "WARN";

    private static boolean sentry_only_log_thrown = true;

    private static boolean unsafe_teleport = false;

    private static int barrel_rows = 3;

    private static boolean ender_chest_six_rows = false;

    private static boolean ender_chest_permission_rows = false;

    private static void loadConfigs() {
        server_mod_name = configuration.getString("misc.server_mod_name.value");
        sentry_dsn = configuration.getString("misc.sentry.dsn");
        sentry_log_level = configuration.getString("misc.sentry.log_level");
        sentry_only_log_thrown = configuration.getBoolean("misc.sentry.only_log_thrown");
        unsafe_teleport = configuration.getBoolean("fixed.unsafe_teleport.enabled");
        barrel_rows = configuration.getInt("misc.ender_chest_six_rows.barrel_rows");
        ender_chest_six_rows = configuration.getBoolean("misc.ender_chest_six_rows.enabled");
        ender_chest_permission_rows = configuration.getBoolean("misc.ender_chest_six_rows.ender_chest_permission_rows");
    }

    private static void saveConfigs() {
        /* server mod name */
        configuration.set("misc.server_mod_name.value", server_mod_name);
        configuration.setComments("misc.server_mod_name.value", List.of(
                "This config is used to custom server brand name"
        ));

        /* Pufferfish Sentry */
        configuration.set("misc.sentry.dsn", sentry_dsn);
        configuration.setComments("misc.sentry.dsn", List.of(
                "Sentry DSN for improved error logging, leave blank to disable,",
                "Obtain from https://sentry.io/"
        ));

        configuration.set("misc.sentry.log_level", sentry_log_level);
        configuration.setComments("misc.sentry.log_level", List.of(
                "Logs with a level higher than or equal to this level will be recorded."
        ));

        configuration.set("misc.sentry.only_log_thrown", sentry_only_log_thrown);
        configuration.setComments("misc.sentry.only_log_thrown", List.of(
                "Only log with a Throwable will be recorded after enabling this."
        ));

        sentry();

        /* Unsafe Teleport */
        configuration.set("fixed.unsafe_teleport.enabled", unsafe_teleport);
        configuration.setComments("fixed.unsafe_teleport.enabled", List.of(
                "If you want to use sand duping,please turn on this.",
                "Warning: This would cause some unsafe issues, you could learn more on : https://github.com/PaperMC/Folia/issues/297."
        ));

        /* Purpur Six Rows Chest */
        configuration.set("misc.ender_chest_six_rows.barrel_rows", barrel_rows);
        configuration.setComments("misc.ender_chest_six_rows.barrel_rows", List.of(
                "The amount of rows a barrel should have. Min: 1, Max: 6"
        ));

        configuration.set("misc.ender_chest_six_rows.enabled", ender_chest_six_rows);
        configuration.setComments("misc.ender_chest_six_rows.enabled", List.of(
                "When enabled, ender chests should have six rows of inventory space."
        ));

        configuration.set("misc.ender_chest_six_rows.ender_chest_permission_rows", ender_chest_permission_rows);
        configuration.setComments("misc.ender_chest_six_rows.ender_chest_permission_rows", List.of(
                "Use permission nodes to determine the number of rows. By default, with this setting enabled, all players have rows unless otherwise specified using permissions."
        ));

        purpurSixRowsChest();
    }

    private static void sentry() {
        String sentryEnvironment = System.getenv("SENTRY_DSN");

        sentry_dsn = sentryEnvironment != null && !sentryEnvironment.isBlank() ? sentryEnvironment: sentry_dsn;

        if (sentry_dsn != null && !sentry_dsn.isBlank()) {
            gg.pufferfish.pufferfish.sentry.SentryManager.init(Level.getLevel(sentry_log_level));
        }
    }

    private static void purpurSixRowsChest() {
        if (barrel_rows > 6 || barrel_rows < 1) {
            logger.warn("Barrel rows cannot is {} , Max: 6, Min: 1", barrel_rows);
            barrel_rows = 3;
        }
    }

    public static String getServerModName() {
        return server_mod_name;
    }

    public static String getSentryDsn() {
        return sentry_dsn;
    }

    public static boolean isSentryOnlyLogThrown() {
        return sentry_only_log_thrown;
    }

    public static boolean isUnsafeTeleport() {
        return unsafe_teleport;
    }

    public static int getBarrelRows() {
        return barrel_rows;
    }

    public static boolean isEnderChestPermissionRows() {
        return ender_chest_permission_rows;
    }

    public static boolean isEnderChestSixRows() {
        return ender_chest_six_rows;
    }
}
