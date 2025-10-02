package me.coderfrish.traium.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import me.coderfrish.traium.command.TraiumCommand;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

import static me.coderfrish.traium.utils.Constants.CONFIG_GLOBAL_FILE;

public class TraiumConfig {
    private static final String CONFIG_NAME_PATTERN = "^[A-Za-z0-9-_]+$";
    private static final CommentedFileConfig configuration;

    static {
        configuration = CommentedFileConfig.builder(CONFIG_GLOBAL_FILE)
                .concurrent().charset(StandardCharsets.UTF_8).build();

        if (CONFIG_GLOBAL_FILE.exists()) {
            configuration.load();
        }
    }

    @ConfigField(type = ConfigTypes.globals, parent = "i18n", comments = {
            "Please use the key from https://minecraft.wiki/w/Language",
            "Format example: en_us zh_cn"
    })
    public static String language = "en_us";

    @ConfigField(type = ConfigTypes.misc, parent = "ender_chest_six_rows", comments = {
            "The amount of rows a barrel should have. Min: 1, Max: 6"
    })
    public static int barrelRows = 3;

    @ConfigField(type = ConfigTypes.misc, parent = "ender_chest_six_rows", comments = {
            "When enabled, ender chests should have six rows of inventory space."
    })
    public static boolean enderChestSixRows = false;

    @ConfigField(type = ConfigTypes.misc, parent = "ender_chest_six_rows", comments = {
            "Use permission nodes to determine the number of rows. By default, with this setting enabled, all players have rows unless otherwise specified using permissions"
    })
    public static boolean enderChestPermissionRows = false;

    @ConfigField(type = ConfigTypes.misc, parent = "server_brand_name", comments = {
            "Server brand name displayed to clients."
    })
    public static String serverBrandName = "Traium";

    public static void setupTraium() {
        new TraiumCommand().register();
    }

    public static void loadAllConfig() throws Exception {
        loadAllConfigValue();
        loadAllConfigComment();
    }

    private static void loadAllConfigValue() throws Exception {
        for (Field field : TraiumConfig.class.getDeclaredFields()) {
            if (!field.isAnnotationPresent(ConfigField.class))
                continue;

            ConfigField configInfo = getConfigAnnotation(field);
            String fullPath = String.format("%s.%s.%s",
                    configInfo.type().keyword(),
                    configInfo.parent(),
                    field.getName()
            );

            if (!configuration.contains(fullPath)) {
                if (field.isAnnotationPresent(Deprecated.class))
                    continue;

                configuration.set(fullPath, field.get(null));
                continue;
            }

            field.set(null, configuration.get(fullPath));
        }
    }

    private static void loadAllConfigComment() {
        for (Field field : TraiumConfig.class.getDeclaredFields()) {
            if (!field.isAnnotationPresent(ConfigField.class))
                continue;

            ConfigField configInfo = getConfigAnnotation(field);
            String fullPath = String.format("%s.%s.%s",
                    configInfo.type().keyword(),
                    configInfo.parent(),
                    field.getName()
            );

            if (field.isAnnotationPresent(Deprecated.class))
                if (!configuration.contains(fullPath))
                    continue;

            addConfigComment(configInfo.comments(), fullPath);
        }

        configuration.save();
    }

    private static ConfigField getConfigAnnotation(Field field) {
        ConfigField configInfo = field.getAnnotation(ConfigField.class);
        checkConfigModifiers(field, "Config Field %s is not public or static.", field.getName());
        checkConfigName(configInfo, "Config Field %s is not a valid config name.", field.getName());

        return configInfo;
    }

    private static void checkConfigName(ConfigField name, String message, Object... formats) {
        if (!name.parent().matches(CONFIG_NAME_PATTERN))
            throw new RuntimeException(String.format(message, formats));
    }

    private static void checkConfigModifiers(Field field, String message, Object... formats) {
        if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isPublic(field.getModifiers()))
            throw new RuntimeException(String.format(message, formats));
    }

    private static void addConfigComment(String[] comment, String fullPath) {
        if (comment.length > 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < comment.length; i++) {
                builder.append(" ").append(comment[i]);
                if (i < comment.length - 1)
                    builder.append("\n");
            }

            configuration.setComment(fullPath, builder.toString());
        }
    }
}
