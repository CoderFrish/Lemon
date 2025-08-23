package me.coderfrish.scheduler;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * This is a scheduler, which is used to be compatible with the old Bukkit plugin and ASM plugin translator, and cannot be used for daily plugin development calls.
 * Plugin develop Use one of {@link io.papermc.paper.threadedregions.scheduler.RegionScheduler},
 *                           {@link io.papermc.paper.threadedregions.scheduler.AsyncScheduler},
 *                           {@link io.papermc.paper.threadedregions.scheduler.EntityScheduler},
 *                           or {@link io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler}
 */
@ApiStatus.Internal
public class TransferScheduler {
    private static final GlobalRegionScheduler globalRegionScheduler = Bukkit.getGlobalRegionScheduler();
    private static final AsyncScheduler asyncScheduler = Bukkit.getAsyncScheduler();
    private static final TransferTaskManager taskManager = new TransferTaskManager();

    public static BukkitTask runTask(Plugin plugin, Runnable task) {
        return setupSyncTask(globalRegionScheduler.run(plugin, t -> task.run()));
    }

    public static BukkitTask runTaskAsynchronously(Plugin plugin, Runnable task) {
        return setupAsyncTask(asyncScheduler.runNow(plugin, t -> task.run()));
    }

    public static void runTask(Plugin plugin, Consumer<BukkitTask> task) {
        globalRegionScheduler.run(plugin, t -> task.accept(setupSyncTask(t)));
    }

    public static void runTaskAsynchronously(Plugin plugin, Consumer<BukkitTask> task) {
        asyncScheduler.runNow(plugin, t -> task.accept(setupAsyncTask(t)));
    }

    public static BukkitTask runTaskLater(Plugin plugin, Runnable task, long delay) {
        return setupSyncTask(globalRegionScheduler.runDelayed(plugin, t -> task.run(), Math.max(delay, 1L)));
    }

    public static BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay) {
        return setupAsyncTask(asyncScheduler.runDelayed(plugin, t -> task.run(), Math.max(delay, 1L) * 50, TimeUnit.MILLISECONDS));
    }

    public static void runTaskLater(Plugin plugin, Consumer<BukkitTask> task, long delay) {
        globalRegionScheduler.runDelayed(plugin, t -> task.accept(setupSyncTask(t)), Math.max(delay, 1L));
    }

    public static void runTaskLaterAsynchronously(Plugin plugin, Consumer<BukkitTask> task, long delay) {
        asyncScheduler.runDelayed(plugin, t -> task.accept(setupAsyncTask(t)), Math.max(delay, 1L) * 50, TimeUnit.MILLISECONDS);
    }

    public static BukkitTask runTaskTimer(Plugin plugin, Runnable task, long delay, long period) {
        return setupSyncTask(globalRegionScheduler.runAtFixedRate(plugin, t -> task.run(), Math.max(delay, 1L), Math.max(period, 1L)));
    }

    public static BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable task, long delay, long period) {
        return setupAsyncTask(asyncScheduler.runAtFixedRate(plugin, t -> task.run(), Math.max(delay, 1L) * 50, Math.max(period, 1L) * 50, TimeUnit.MILLISECONDS));
    }

    public static void runTaskTimer(Plugin plugin, Consumer<BukkitTask> task, long delay, long period) {
        globalRegionScheduler.runAtFixedRate(plugin, t -> task.accept(setupSyncTask(t)), Math.max(delay, 1L), Math.max(period, 1L));
    }

    public static void runTaskTimerAsynchronously(Plugin plugin, Consumer<BukkitTask> task, long delay, long period) {
        asyncScheduler.runAtFixedRate(plugin, t -> task.accept(setupAsyncTask(t)), Math.max(delay, 1L) * 50, Math.max(period, 1L) * 50, TimeUnit.MILLISECONDS);
    }

    public static void cancelTasks(Plugin plugin) {
        globalRegionScheduler.cancelTasks(plugin);
        asyncScheduler.cancelTasks(plugin);
    }

    public static void cancelTask(int id) {
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

    private static BukkitTask setupAsyncTask(ScheduledTask task) {
        return new TransferAsyncTask(task);
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

    private static final class TransferAsyncTask implements BukkitTask {
        private final ScheduledTask task;

        private TransferAsyncTask(ScheduledTask task) {
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
            return false;
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
