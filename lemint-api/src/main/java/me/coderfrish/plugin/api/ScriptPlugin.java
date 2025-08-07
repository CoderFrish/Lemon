package me.coderfrish.plugin.api;

import me.coderfrish.plugin.ScriptPluginManager;
import org.bukkit.plugin.PluginBase;

import java.io.File;

public class ScriptPlugin {
    private boolean enable = false;
    private final ScriptPluginMeta meta;

    public final ScriptConfig config;
    public final ScriptEvent event;
    public final ScriptCommand command;
    public final ScriptPermission permission;

    public ScriptPlugin(ScriptPluginMeta meta) {
        this.meta = meta;
        this.config = new ScriptConfig(this);
        this.event = new ScriptEvent(this);
        this.command = new ScriptCommand(this, getPluginMeta());
        this.permission = new ScriptPermission();
    }

    public ScriptPluginMeta getPluginMeta() {
        return this.meta;
    }

    public File getDataFolder() {
        return getBukkitJavaPlugin().getDataFolder();
    }

    private PluginBase getBukkitJavaPlugin() {
        return ScriptPluginManager.getJavaPlugins().get(this);
    }

    public synchronized boolean isEnable() {
        return enable;
    }

    public synchronized void setEnable(boolean enable) {
        if (enable) {
            ScriptPluginManager.enablePlugin(this);
        } else {
            ScriptPluginManager.disablePlugin(this);
        }
        this.enable = enable;
    }
}
