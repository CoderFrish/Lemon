package me.coderfrish.plugin.scheduler;

import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.LuaFunction;

public interface GlobalRegionScheduler {
    void execute(@NotNull me.coderfrish.plugin.Plugin plugin, @NotNull LuaFunction run);

    void run(@NotNull me.coderfrish.plugin.Plugin plugin, @NotNull LuaFunction task);

    void runDelayed(@NotNull me.coderfrish.plugin.Plugin plugin, @NotNull LuaFunction task, long delayTicks);

    void runAtFixedRate(@NotNull me.coderfrish.plugin.Plugin plugin, @NotNull LuaFunction task,
                                                                                     long initialDelayTicks, long periodTicks);
    void cancelTasks(@NotNull me.coderfrish.plugin.Plugin plugin);
}
