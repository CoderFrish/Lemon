package me.coderfrish.plugin.scheduler;

import me.coderfrish.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaFunction;

public interface EntityScheduler {
    boolean execute(@NotNull Plugin plugin, @NotNull LuaFunction run, @Nullable LuaFunction retired, long delay);

    void run(@NotNull Plugin plugin, @NotNull LuaFunction task, @Nullable LuaFunction retired);

    void runDelayed(@NotNull Plugin plugin, @NotNull LuaFunction task, @Nullable LuaFunction retired, long delayTicks);

    void runAtFixedRate(@NotNull Plugin plugin, @NotNull LuaFunction task, @Nullable LuaFunction retired, long initialDelayTicks, long periodTicks);
}
