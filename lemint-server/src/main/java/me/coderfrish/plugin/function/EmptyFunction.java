package me.coderfrish.plugin.function;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class EmptyFunction extends ZeroArgFunction {
    public static final LuaFunction EMPTY_FUNCTION = new EmptyFunction();

    @Override
    public LuaValue call() {
        return NIL;
    }
}
