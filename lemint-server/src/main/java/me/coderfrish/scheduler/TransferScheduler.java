package me.coderfrish.scheduler;

import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;

public class TransferScheduler {
    private static final GlobalRegionScheduler globalRegionScheduler = Bukkit.getGlobalRegionScheduler();
    private static final TransferTaskManager taskManager = new TransferTaskManager();

    public static BukkitTask runTask(BukkitScheduler ignore, Plugin plugin, Runnable task) {
        return setupSyncTask(globalRegionScheduler.run(plugin, t -> task.run()));
    }

    public static void runTask(BukkitScheduler ignore, Plugin plugin, Consumer<BukkitTask> task) {
        globalRegionScheduler.run(plugin, t -> task.accept(setupSyncTask(t)));
    }

    public static BukkitTask runTaskLater(BukkitScheduler ignore, Plugin plugin, Runnable task, long delay) {
        return setupSyncTask(globalRegionScheduler.runDelayed(plugin, t -> task.run(), Math.max(delay, 1L)));
    }

    public static void runTaskLater(BukkitScheduler ignore, Plugin plugin, Consumer<BukkitTask> task, long delay) {
        globalRegionScheduler.runDelayed(plugin, t -> task.accept(setupSyncTask(t)), Math.max(delay, 1L));
    }

    public static BukkitTask runTaskTimer(BukkitScheduler ignore, Plugin plugin, Runnable task, long delay, long period) {
        return setupSyncTask(globalRegionScheduler.runAtFixedRate(plugin, t -> task.run(), Math.max(delay, 1L), Math.max(period, 1L)));
    }

    public static void runTaskTimer(BukkitScheduler ignore, Plugin plugin, Consumer<BukkitTask> task, long delay, long period) {
        globalRegionScheduler.runAtFixedRate(plugin, t -> task.accept(setupSyncTask(t)), Math.max(delay, 1L), Math.max(period, 1L));
    }

    public static void cancelTasks(BukkitScheduler ignore, Plugin plugin) {
        globalRegionScheduler.cancelTasks(plugin);
    }

    public static void cancelTask(BukkitScheduler ignore, int id) {
        BukkitTask element = taskManager.getElement(id);
        if (element != null) {
            element.cancel();
        } else {
            throw new RuntimeException("Task with id " + id + " not found.");
        }
    }

    private static BukkitTask setupSyncTask(ScheduledTask task) {
        return new TransferSyncTask(task);
    }

    private static final class TransferSyncTask implements BukkitTask {
        private final ScheduledTask task;

        private TransferSyncTask(ScheduledTask task) {
            this.task = task;
            taskManager.push(this);
        }

        @Override
        public int getTaskId() {
            return taskManager.getId(this);
        }

        @Override
        public @NotNull Plugin getOwner() {
            return task.getOwningPlugin();
        }

        @Override
        public boolean isSync() {
            return true;
        }

        @Override
        public boolean isCancelled() {
            return task.isCancelled();
        }

        @Override
        public void cancel() {
            task.cancel();
            taskManager.pop(this);
        }
    }
}
