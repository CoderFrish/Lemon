package me.coderfrish.plugin;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;

public interface PluginManager {
    public void register(LuaFunction plugin, LuaTable table);
}
