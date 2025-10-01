package me.coderfrish.traium.configuration;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

import static me.coderfrish.traium.TraiumConstants.CONFIG_FILE;

public class TraiumConfig {
    private static final String CONFIG_NAME_PATTERN = "^[A-Za-z0-9-_]+$";
    private static final CommentedFileConfig configuration;

    static {
        configuration = CommentedFileConfig.builder(CONFIG_FILE)
                .concurrent().charset(StandardCharsets.UTF_8).build();

        if (CONFIG_FILE.exists()) {
            configuration.load();
        }
    }

    @ConfigField(type = ConfigTypes.misc, parent = "ender_chest_six_rows")
    public static int barrelRows = 3;

    @ConfigField(type = ConfigTypes.misc, parent = "ender_chest_six_rows")
    public static boolean enderChestSixRows = false;

    @ConfigField(type = ConfigTypes.misc, parent = "ender_chest_six_rows")
    public static boolean enderChestPermissionRows = false;

    public static void setupTraium() {
    }

    public static void loadAllConfig() throws Exception {
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

        configuration.save();
    }

    private static ConfigField getConfigAnnotation(Field field) {
        ConfigField configInfo = field.getAnnotation(ConfigField.class);
        checkConfigModifiers(field, "Config Field " + field.getName() + " is not public or static.");
        checkConfigName(configInfo.parent(), "Config Field " + field.getName() + " is not a valid config name.");

        return configInfo;
    }

    private static void checkConfigName(String name, String message) {
        if (!name.matches(CONFIG_NAME_PATTERN))
            throw new RuntimeException(message);
    }

    private static void checkConfigModifiers(Field field, String message) {
        if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isPublic(field.getModifiers()))
            throw new RuntimeException(message);
    }
}
