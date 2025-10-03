package me.coderfrish.traium.scheduler;

import io.papermc.paper.threadedregions.scheduler.FoliaGlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import net.minecraft.core.BlockPos;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SchedulerWrapper {
    private static final List<ScheduledTask> wrappedScheduledTasks = new CopyOnWriteArrayList<>();
    private static final Map<ScheduledTask, ScheduledTask> globalRegionToEntityTasks = new ConcurrentHashMap<>();
    private static final Map<ScheduledTask, ScheduledTask> globalRegionToRegionTasks = new ConcurrentHashMap<>();

    public static void wrapEntity(Entity entity, FoliaGlobalRegionScheduler.GlobalScheduledTask globalRegionTask, long delay, long period) {
        if (wrappedScheduledTasks.contains(globalRegionTask)) return;
        ScheduledTask task;
        if (period == -1) {
            task = entity.getScheduler().runDelayed(globalRegionTask.getOwningPlugin(), globalRegionTask.run, null, delay);
        } else {
            task = entity.getScheduler().runAtFixedRate(globalRegionTask.getOwningPlugin(), globalRegionTask.run, null, delay, period);
        }

        cancel(globalRegionTask);
        globalRegionToEntityTasks.put(globalRegionTask, task);
        wrappedScheduledTasks.add(globalRegionTask);
    }

    public static void wrapRegion(World world, BlockPos blockPos, FoliaGlobalRegionScheduler.GlobalScheduledTask globalRegionTask, long delay, long period) {
        if (wrappedScheduledTasks.contains(globalRegionTask)) return;
        ScheduledTask task;
        if (period == -1) {
            task = Bukkit.getRegionScheduler().runDelayed(
                    globalRegionTask.getOwningPlugin(), world, blockPos.getZ() >> 4, blockPos.getZ() >> 4, globalRegionTask.run, delay);
        } else {
            task = Bukkit.getRegionScheduler().runAtFixedRate(
                    globalRegionTask.getOwningPlugin(), world, blockPos.getZ() >> 4, blockPos.getZ() >> 4, globalRegionTask.run, delay, period);
        }

        cancel(globalRegionTask);
        globalRegionToRegionTasks.put(globalRegionTask, task);
        wrappedScheduledTasks.add(globalRegionTask);
    }

    public static void cancel(FoliaGlobalRegionScheduler.GlobalScheduledTask globalRegionTask) {
        if (wrappedScheduledTasks.contains(globalRegionTask)) {
            if (globalRegionToEntityTasks.containsKey(globalRegionTask)) {
                globalRegionToEntityTasks.get(globalRegionTask).cancel();
                globalRegionToEntityTasks.remove(globalRegionTask);
            }

            if (globalRegionToRegionTasks.containsKey(globalRegionTask)) {
                globalRegionToRegionTasks.get(globalRegionTask).cancel();
                globalRegionToRegionTasks.remove(globalRegionTask);
            }

            wrappedScheduledTasks.remove(globalRegionTask);
            return;
        }

        globalRegionTask.cancel();
    }

    public static boolean isWrappedScheduledTask(ScheduledTask task) {
        return wrappedScheduledTasks.contains(task);
    }
}
