package me.coderfrish.plugin;

import me.coderfrish.functions.EmptyFunction;
import org.luaj.vm2.LuaFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Plugin {
    public final PluginMeta meta;
    public final Logger logger;
    public final PluginEvent event;
    public final PluginCommand command;

    public Plugin(PluginMeta meta) {
        this.meta = meta;
        JavaPluginManager.addPlugin(this);

        this.logger = LoggerFactory.getLogger(meta.getName());
        this.event = new PluginEvent(this);
        this.command = new PluginCommand(this);
    }

    public LuaFunction load = EmptyFunction.EMPTY_FUNCTION;
    public LuaFunction enable = EmptyFunction.EMPTY_FUNCTION;
    public LuaFunction disable = EmptyFunction.EMPTY_FUNCTION;
}
