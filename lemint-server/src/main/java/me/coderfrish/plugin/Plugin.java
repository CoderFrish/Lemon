package me.coderfrish.plugin;

import me.coderfrish.functions.EmptyFunction;
import org.luaj.vm2.LuaFunction;

public class Plugin {
    public final PluginMeta meta;
    public final PluginEvent event;
    public final PluginCommand command;
    public final PluginConfig config;

    public Plugin(PluginMeta meta) {
        this.meta = meta;
        JavaPluginManager.addPlugin(this);

        this.event = new PluginEvent(this);
        this.command = new PluginCommand(this);
        this.config = new PluginConfig(this);
    }

    public LuaFunction load = EmptyFunction.EMPTY_FUNCTION;
    public LuaFunction enable = EmptyFunction.EMPTY_FUNCTION;
    public LuaFunction disable = EmptyFunction.EMPTY_FUNCTION;
}
