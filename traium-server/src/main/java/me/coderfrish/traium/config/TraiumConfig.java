package me.coderfrish.traium.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import me.coderfrish.traium.command.TraiumCommand;
import me.coderfrish.traium.command.TraiumPermission;
import org.bukkit.Bukkit;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

public class TraiumConfig {
    private static final String CONFIG_NAME_PATTERN = "^[A-Za-z0-9-_]+$";
    private final CommentedFileConfig configuration;

    public TraiumConfig(File configFile) {
        configuration = CommentedFileConfig.builder(configFile)
                .charset(StandardCharsets.UTF_8).build();

        if (configFile.exists()) {
            configuration.load();
        }

        try {
            this.loadAllConfigValue();
            this.loadAllConfigComment();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static GlobalConfig globalConfig;

    public static void setupTraium() {
        // Register Permission node.
        for (TraiumPermission value : TraiumPermission.values()) {
            Bukkit.getPluginManager().addPermission(value.permission());
        }
        // Register Command.
        new TraiumCommand().register();

        /* setup sentry */
        me.coderfrish.traium.utils.SentrySetup.setup(globalConfig);
    }

    public static void loadAllConfig() throws Exception {
        globalConfig = new GlobalConfig();
        new WorldConfig();
    }

    public CommentedFileConfig getCurrentConfig() {
        return configuration;
    }

    private void loadAllConfigValue() throws Exception {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(ConfigField.class))
                continue;

            ConfigField configInfo = getConfigAnnotation(field);
            String fullPath = String.format("%s.%s.%s",
                    configInfo.type().keyword(),
                    configInfo.parent(),
                    configInfo.name().isBlank() ? field.getName() : configInfo.name()
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

    private void loadAllConfigComment() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(ConfigField.class))
                continue;

            ConfigField configInfo = getConfigAnnotation(field);
            String fullPath = String.format("%s.%s.%s",
                    configInfo.type().keyword(),
                    configInfo.parent(),
                    configInfo.name().isBlank() ? field.getName() : configInfo.name()
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

    private void addConfigComment(String[] comment, String fullPath) {
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
