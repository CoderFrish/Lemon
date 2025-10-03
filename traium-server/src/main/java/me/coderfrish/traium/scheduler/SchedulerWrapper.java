package me.coderfrish.traium.scheduler;

import io.papermc.paper.threadedregions.scheduler.FoliaGlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SchedulerWrapper {
    private static final List<ScheduledTask> wrappedScheduledTasks = new CopyOnWriteArrayList<>();
    private static final Map<ScheduledTask, ScheduledTask> regionToEntityTasks = new ConcurrentHashMap<>();

    public static void wrap(Entity entity, FoliaGlobalRegionScheduler.GlobalScheduledTask globalRegionTask, long delay, long period) {
        if (wrappedScheduledTasks.contains(globalRegionTask)) return;
        ScheduledTask task;
        if (period == -1) {
            task = entity.getScheduler().runDelayed(globalRegionTask.getOwningPlugin(), globalRegionTask.run, null, delay);
        } else {
            task = entity.getScheduler().runAtFixedRate(globalRegionTask.getOwningPlugin(), globalRegionTask.run, null, delay, period);
        }

        cancel(globalRegionTask);
        regionToEntityTasks.put(globalRegionTask, task);
        wrappedScheduledTasks.add(globalRegionTask);
    }

    public static void cancel(FoliaGlobalRegionScheduler.GlobalScheduledTask globalRegionTask) {
        if (wrappedScheduledTasks.contains(globalRegionTask)) {
            regionToEntityTasks.get(globalRegionTask).cancel();
            wrappedScheduledTasks.remove(globalRegionTask);
            regionToEntityTasks.remove(globalRegionTask);
            return;
        }

        globalRegionTask.cancel();
    }

    public static boolean isWrappedScheduledTask(ScheduledTask task) {
        return wrappedScheduledTasks.contains(task);
    }
}
