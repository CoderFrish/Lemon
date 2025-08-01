package me.coderfrish.server.plugin;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

public class PluginEvent {
    public LuaFunction listen = new TwoArgFunction() {
        @Override
        public LuaValue call(LuaValue evetName, LuaValue listener) {
            PluginManager.eventManager.register(evetName, (LuaFunction) listener);
            return NIL;
        }
    };
}
