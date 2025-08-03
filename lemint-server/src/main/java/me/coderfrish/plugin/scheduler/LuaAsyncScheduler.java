package me.coderfrish.plugin.scheduler;

import me.coderfrish.plugin.JavaPluginManager;
import me.coderfrish.plugin.Plugin;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.util.concurrent.TimeUnit;

public class LuaAsyncScheduler implements AsyncScheduler {
    @Override
    public void runNow(@NotNull Plugin plugin, @NotNull LuaFunction task) {
        Bukkit.getAsyncScheduler().runNow(JavaPluginManager.getPlugin(plugin), (st) -> task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st))));
    }

    @Override
    public void runDelayed(@NotNull Plugin plugin, LuaFunction task, long delay, @NotNull TimeUnit unit) {
        Bukkit.getAsyncScheduler().runDelayed(JavaPluginManager.getPlugin(plugin), (st) -> task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st))), delay, unit);
    }

    @Override
    public void runAtFixedRate(@NotNull Plugin plugin, @NotNull LuaFunction task, long initialDelay, long period, @NotNull TimeUnit unit) {
        Bukkit.getAsyncScheduler().runAtFixedRate(JavaPluginManager.getPlugin(plugin), (st) -> task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st))), initialDelay, period, unit);
    }

    @Override
    public void cancelTasks(@NotNull Plugin plugin) {
        Bukkit.getAsyncScheduler().cancelTasks(JavaPluginManager.getPlugin(plugin));
    }
}
