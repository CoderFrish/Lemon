package me.coderfrish.plugin.pack;

import java.util.List;

public class PluginPack {
    private final String sha;
    private final int version;
    private final String main;
    private final List<PackEntry> entries;

    public PluginPack(int version, String main, String sha, List<PackEntry> entries) {
        this.sha = sha;
        this.version = version;
        this.main = main;
        this.entries = entries;
    }

    public String getSha() {
        return sha;
    }

    public int getVersion() {
        return version;
    }

    public List<PackEntry> getEntries() {
        return entries;
    }

    public String getMain() {
        return main;
    }
}
