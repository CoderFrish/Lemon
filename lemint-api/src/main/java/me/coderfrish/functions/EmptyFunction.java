package me.coderfrish.functions;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class EmptyFunction extends ZeroArgFunction {
    public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();

    @Override
    public LuaValue call() {
        return NIL;
    }
}
