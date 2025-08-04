package me.coderfrish.plugin.scheduler;

import me.coderfrish.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.LuaFunction;

import java.util.concurrent.TimeUnit;

public interface AsyncScheduler {
    void runNow(@NotNull Plugin plugin, @NotNull LuaFunction task);

    void runDelayed(@NotNull Plugin plugin, LuaFunction task, long delay, @NotNull TimeUnit unit);

    void runAtFixedRate(@NotNull Plugin plugin, @NotNull LuaFunction task, long initialDelay, long period, @NotNull TimeUnit unit);

    void cancelTasks(@NotNull Plugin plugin);
}
