package me.coderfrish.plugin;

import me.coderfrish.plugin.scheduler.AsyncScheduler;
import me.coderfrish.plugin.scheduler.GlobalRegionScheduler;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;

public interface PluginManager {
    public void register(LuaFunction plugin, LuaTable table);

    public GlobalRegionScheduler globalRegionScheduler();

    public AsyncScheduler asyncScheduler();
}
