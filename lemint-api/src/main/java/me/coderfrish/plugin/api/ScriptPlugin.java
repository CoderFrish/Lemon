package me.coderfrish.plugin.api;

import me.coderfrish.plugin.ScriptPluginManager;

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
        this.command = new ScriptCommand(this, getMeta());
        this.permission = new ScriptPermission();
    }

    public ScriptPluginMeta getMeta() {
        return this.meta;
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
