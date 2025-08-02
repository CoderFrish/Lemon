package me.coderfrish.plugin;

import me.coderfrish.plugin.function.EmptyFunction;
import me.coderfrish.server.plugin.PluginCommand;
import me.coderfrish.server.plugin.PluginEvent;
import me.coderfrish.server.plugin.PluginLogger;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class Plugin {
    public final LuaTable meta;
    public final LuaValue logger;
    public final LuaValue event;
    public final LuaValue command;

    public Plugin(LuaTable meta) {
        this.meta = meta;

        String pluginName = meta.get("name").tojstring();
        this.logger = CoerceJavaToLua.coerce(new PluginLogger(pluginName));
        this.event = CoerceJavaToLua.coerce(new PluginEvent());
        this.command = CoerceJavaToLua.coerce(new PluginCommand());
    }

    public LuaFunction init = EmptyFunction.EMPTY_FUNCTION;
    public LuaFunction enable = EmptyFunction.EMPTY_FUNCTION;
    public LuaFunction disable = EmptyFunction.EMPTY_FUNCTION;
}
