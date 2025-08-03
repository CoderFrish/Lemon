package me.coderfrish.plugin.scheduler;

import me.coderfrish.plugin.JavaPluginManager;
import me.coderfrish.plugin.Plugin;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class LuaEntityScheduler implements EntityScheduler {
    private final Entity entity;

    public LuaEntityScheduler(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean execute(@NotNull Plugin plugin, @NotNull LuaFunction run, @Nullable LuaFunction retired, long delay) {
        return entity.getScheduler().execute(JavaPluginManager.getPlugin(plugin), run::call, () -> {
            if (retired != null) {
                retired.call();
            }
        }, delay);
    }

    @Override
    public void run(@NotNull Plugin plugin, @NotNull LuaFunction task, @Nullable LuaFunction retired) {
        entity.getScheduler().run(JavaPluginManager.getPlugin(plugin), (st) -> {
            task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st)));
        }, () -> {
            if (retired != null) {
                retired.call();
            }
        });
    }

    @Override
    public void runDelayed(@NotNull Plugin plugin, @NotNull LuaFunction task, @Nullable LuaFunction retired, long delayTicks) {
        entity.getScheduler().runDelayed(JavaPluginManager.getPlugin(plugin), (st) -> {
            task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st)));
        }, () -> {
            if (retired != null) {
                retired.call();
            }
        }, delayTicks);
    }

    @Override
    public void runAtFixedRate(@NotNull Plugin plugin, @NotNull LuaFunction task, @Nullable LuaFunction retired, long initialDelayTicks, long periodTicks) {
        entity.getScheduler().runAtFixedRate(JavaPluginManager.getPlugin(plugin), (st) -> {
            task.call(CoerceJavaToLua.coerce(new LuaScheduledTask(st)));
        }, () -> {
            if (retired != null) {
                retired.call();
            }
        }, initialDelayTicks, periodTicks);
    }
}
