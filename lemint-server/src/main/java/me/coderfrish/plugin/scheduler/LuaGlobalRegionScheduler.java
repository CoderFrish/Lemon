package me.coderfrish.plugin.scheduler;

import me.coderfrish.plugin.JavaPluginManager;
import me.coderfrish.plugin.Plugin;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class LuaGlobalRegionScheduler implements GlobalRegionScheduler {
    @Override
    public void execute(@NotNull Plugin plugin, @NotNull LuaFunction run) {
        Bukkit.getGlobalRegionScheduler().execute(JavaPluginManager.getPlugin(plugin), run::call);
    }

    @Override
    public void run(@NotNull Plugin plugin, @NotNull LuaFunction task) {
        Bukkit.getGlobalRegionScheduler().run(JavaPluginManager.getPlugin(plugin), (st) -> task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st))));
    }

    @Override
    public void runDelayed(@NotNull Plugin plugin, @NotNull LuaFunction task, long delayTicks) {
        Bukkit.getGlobalRegionScheduler().runDelayed(JavaPluginManager.getPlugin(plugin), (st) -> task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st))), delayTicks);
    }

    @Override
    public void runAtFixedRate(@NotNull Plugin plugin, @NotNull LuaFunction task, long initialDelayTicks, long periodTicks) {
        Bukkit.getGlobalRegionScheduler().runAtFixedRate(JavaPluginManager.getPlugin(plugin), (st) -> task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st))), initialDelayTicks, periodTicks);
    }

    @Override
    public void cancelTasks(@NotNull Plugin plugin) {
        Bukkit.getGlobalRegionScheduler().cancelTasks(JavaPluginManager.getPlugin(plugin));
    }
}
