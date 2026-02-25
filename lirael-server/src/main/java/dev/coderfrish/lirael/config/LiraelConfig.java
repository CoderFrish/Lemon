package dev.coderfrish.lirael.config;

import org.apache.logging.log4j.Level;
import org.bukkit.configuration.file.YamlConfiguration;

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

    private static void loadConfigs() {
        server_mod_name = configuration.getString("misc.server_mod_name.value");
        sentry_dsn = configuration.getString("misc.sentry.dsn");
        sentry_log_level = configuration.getString("misc.sentry.log_level");
        sentry_only_log_thrown = configuration.getBoolean("misc.sentry.only_log_thrown");
        unsafe_teleport = configuration.getBoolean("fixed.unsafe_teleport.enabled");
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
    }

    private static void sentry() {
        String sentryEnvironment = System.getenv("SENTRY_DSN");

        sentry_dsn = sentryEnvironment != null && !sentryEnvironment.isBlank() ? sentryEnvironment: sentry_dsn;

        if (sentry_dsn != null && !sentry_dsn.isBlank()) {
            gg.pufferfish.pufferfish.sentry.SentryManager.init(Level.getLevel(sentry_log_level));
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
}
