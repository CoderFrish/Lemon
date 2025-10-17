package me.coderfrish.traium.utils;

import java.io.File;

public class Constants {
    public static final File CONFIG_FOLDER = new File("traium_config");
    public static final File PROFILER_FOLDER = new File("profiler");
    public static final FakePlugin FAKE_PLUGIN = new FakePlugin();

    static {
        if (!CONFIG_FOLDER.exists()) {
            CONFIG_FOLDER.mkdirs();
        }
    }
}
