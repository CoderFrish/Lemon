package me.coderfrish.plugin.scheduler;

import me.coderfrish.plugin.Plugin;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.LuaFunction;

public interface RegionScheduler {
    void execute(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull LuaFunction run);

    default void execute(@NotNull Plugin plugin, @NotNull Location location, @NotNull LuaFunction run) {
        this.execute(plugin, location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4, run);
    }

    void run(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull LuaFunction task);

    default void run(@NotNull Plugin plugin, @NotNull Location location, @NotNull LuaFunction task) {
        this.run(plugin, location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4, task);
    }

    void runDelayed(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull LuaFunction task, long delayTicks);

    default void runDelayed(@NotNull Plugin plugin, @NotNull Location location, @NotNull LuaFunction task, long delayTicks) {
        this.runDelayed(plugin, location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4, task, delayTicks);
    }

    void runAtFixedRate(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull LuaFunction task, long initialDelayTicks, long periodTicks);

    default void runAtFixedRate(@NotNull Plugin plugin, @NotNull Location location, @NotNull LuaFunction task, long initialDelayTicks, long periodTicks) {
        this.runAtFixedRate(plugin, location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4, task, initialDelayTicks, periodTicks);
    }
}
