package me.coderfrish.plugin;

import me.coderfrish.plugin.function.EmptyFunction;
import me.coderfrish.server.plugin.LuaLogger;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class Plugin {
    public LuaTable meta;
    public LuaValue logger;

    public Plugin(LuaTable meta) {
        this.meta = meta;
        this.logger = CoerceJavaToLua.coerce(new LuaLogger(meta.get("name").tojstring()));
    }

    public LuaFunction init = EmptyFunction.EMPTY_FUNCTION;
    public LuaFunction enable = EmptyFunction.EMPTY_FUNCTION;
    public LuaFunction disable = EmptyFunction.EMPTY_FUNCTION;
}
