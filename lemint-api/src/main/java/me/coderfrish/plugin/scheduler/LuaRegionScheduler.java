package me.coderfrish.plugin.scheduler;

import me.coderfrish.plugin.JavaPluginManager;
import me.coderfrish.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class LuaRegionScheduler implements RegionScheduler {
    @Override
    public void execute(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull LuaFunction run) {
        Bukkit.getRegionScheduler().execute(JavaPluginManager.getPlugin(plugin), world, chunkX, chunkZ, run::call);
    }

    @Override
    public void run(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull LuaFunction task) {
        Bukkit.getRegionScheduler().run(JavaPluginManager.getPlugin(plugin), world, chunkX, chunkZ, (st) -> {
            task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st)));
        });
    }

    @Override
    public void runDelayed(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull LuaFunction task, long delayTicks) {
        Bukkit.getRegionScheduler().runDelayed(JavaPluginManager.getPlugin(plugin), world, chunkX, chunkZ, (st) -> {
            task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st)));
        },  delayTicks);
    }

    @Override
    public void runAtFixedRate(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull LuaFunction task, long initialDelayTicks, long periodTicks) {
        Bukkit.getRegionScheduler().runAtFixedRate(JavaPluginManager.getPlugin(plugin), world, chunkX, chunkZ, (st) -> {
            task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st)));
        }, initialDelayTicks, periodTicks);
    }
}
