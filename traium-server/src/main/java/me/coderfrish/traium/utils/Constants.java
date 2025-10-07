package me.coderfrish.traium.utils;

import java.io.File;

public class Constants {
    public static final File CONFIG_FOLDER = new File("traium_config");

    static {
        if (!CONFIG_FOLDER.exists()) {
            CONFIG_FOLDER.mkdirs();
        }
    }
}
