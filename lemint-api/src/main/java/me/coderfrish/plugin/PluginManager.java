package me.coderfrish.plugin;

import me.coderfrish.plugin.scheduler.AsyncScheduler;
import me.coderfrish.plugin.scheduler.EntityScheduler;
import me.coderfrish.plugin.scheduler.GlobalRegionScheduler;
import me.coderfrish.plugin.scheduler.RegionScheduler;
import org.bukkit.entity.Entity;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;

public interface PluginManager {
    public void register(LuaFunction plugin, LuaTable table);

    public GlobalRegionScheduler globalRegionScheduler();

    public AsyncScheduler asyncScheduler();

    public RegionScheduler regionScheduler();

    public EntityScheduler entityScheduler(Entity entity);
}
