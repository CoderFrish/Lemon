package me.coderfrish.functions;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class ImportFunction extends OneArgFunction {
    @Override
    public LuaValue call(LuaValue arg) {
        try {
            return CoerceJavaToLua.coerce(Class.forName(arg.tojstring()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
