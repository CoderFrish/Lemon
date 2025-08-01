package me.coderfrish.server.plugin;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginLogger {
    private final Logger logger;

    public PluginLogger(String name) {
        this.logger = LoggerFactory.getLogger(name);
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
