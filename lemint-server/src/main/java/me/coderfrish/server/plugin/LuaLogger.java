package me.coderfrish.server.plugin;

import me.coderfrish.plugin.PluginLogger;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class LuaLogger {
    private final PluginLogger logger;

    public LuaLogger(String name) {
        this.logger = new me.coderfrish.server.plugin.PluginLogger(name);
    }

    public LuaFunction info = new OneArgFunction() {
        @Override
        public LuaValue call(LuaValue arg) {
            logger.info(arg.tojstring());
            return NIL;
        }
    };

    public LuaFunction warn = new OneArgFunction() {
        @Override
        public LuaValue call(LuaValue arg) {
            logger.warn(arg.tojstring());
            return NIL;
        }
    };

    public LuaFunction error = new OneArgFunction() {
        @Override
        public LuaValue call(LuaValue arg) {
            logger.error(arg.tojstring());
            return NIL;
        }
    };
}
