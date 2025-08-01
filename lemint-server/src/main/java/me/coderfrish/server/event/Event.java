package me.coderfrish.server.event;

import me.coderfrish.server.plugin.PluginManager;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.util.concurrent.CopyOnWriteArrayList;

public class Event {
    public void call() {
        CopyOnWriteArrayList<LuaFunction> listener = PluginManager.eventManager.getEvent(this.getClass());

        for (LuaFunction function : listener) {
            function.call(CoerceJavaToLua.coerce(this));
        }
    }
}
