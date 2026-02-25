package dev.coderfrish.lirael.config;

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

    private static void loadConfigs() {
        server_mod_name = configuration.getString("misc.server_mod_name.value");
    }

    private static void saveConfigs() {
        /* server mod name */
        configuration.set("misc.server_mod_name.value", server_mod_name);
        configuration.setComments("misc.server_mod_name.value", List.of(
                "This config is used to custom server brand name"
        ));
    }

    public static String getServerModName() {
        return server_mod_name;
    }
}
