package me.coderfrish.plugin;

import org.graalvm.polyglot.Value;

public class ScriptPluginMeta {
    private final String name;
    private final String version;
    private final String description;

    public ScriptPluginMeta(String name, String version, String description, Value installer) {
        this.name = name;
        this.version = version;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }
}
