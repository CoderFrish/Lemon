package me.coderfrish.scheduler;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.craftbukkit.scheduler.CraftScheduler;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

import static me.coderfrish.utility.FutureUtility.toFuture;

@Deprecated
public class FixedCraftScheduler extends CraftScheduler {
    public static final FixedScheduleTaskMgr tasks = new FixedScheduleTaskMgr();
    public final GlobalRegionScheduler globalSyncScheduler;
    public final AsyncScheduler globalAsyncScheduler;

    public FixedCraftScheduler(GlobalRegionScheduler globalSyncScheduler, AsyncScheduler asyncScheduler) {
        this.globalSyncScheduler = globalSyncScheduler;
        this.globalAsyncScheduler = asyncScheduler;
    }

    @Override
    public void cancelTask(int taskId) {
        tasks.getElement(taskId).cancel();
    }

    @Override
    public void cancelTasks(@NotNull Plugin plugin) {
        for (BukkitTask task : tasks) {
            if (task.getOwner() == plugin) {
                task.cancel();
            }
        }
    }

    @Override
    public @NotNull BukkitTask runTask(@NotNull Plugin plugin, @NotNull Runnable task) throws IllegalArgumentException {
        return FixedScheduledTask.setupSyncTask(globalSyncScheduler.run(plugin, scheduledTask -> task.run()));
    }

    @Override
    public void runTask(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task) throws IllegalArgumentException {
        globalSyncScheduler.run(plugin, scheduledTask -> task.accept(FixedScheduledTask.setupSyncTask(scheduledTask)));
    }

    @Override
    public @NotNull BukkitTask runTaskAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task) throws IllegalArgumentException {
        return FixedScheduledTask.setupAsyncTask(globalAsyncScheduler.runNow(plugin, scheduledTask -> task.run()));
    }

    @Override
    public void runTaskAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task) throws IllegalArgumentException {
        globalAsyncScheduler.runNow(plugin, scheduledTask -> task.accept(FixedScheduledTask.setupAsyncTask(scheduledTask)));
    }

    @Override
    public @NotNull BukkitTask runTaskLater(@NotNull Plugin plugin, @NotNull Runnable task, long delay) throws IllegalArgumentException {
        delay = Math.max(1L, delay);
        return FixedScheduledTask.setupSyncTask(globalSyncScheduler.runDelayed(plugin, scheduledTask -> task.run(), delay));
    }

    @Override
    public void runTaskLater(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay) throws IllegalArgumentException {
        delay = Math.max(1L, delay);
        globalSyncScheduler.runDelayed(plugin, scheduledTask -> task.accept(FixedScheduledTask.setupSyncTask(scheduledTask)), delay);
    }

    @Override
    public @NotNull BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay) throws IllegalArgumentException {
        delay = Math.max(1L, delay);
        return FixedScheduledTask.setupAsyncTask(globalAsyncScheduler.runDelayed(plugin, scheduledTask -> task.run(), delay, TimeUnit.MICROSECONDS));
    }

    @Override
    public void runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay) throws IllegalArgumentException {
        delay = Math.max(1L, delay);
        globalAsyncScheduler.runDelayed(plugin, scheduledTask -> task.accept(FixedScheduledTask.setupAsyncTask(scheduledTask)), delay, TimeUnit.MICROSECONDS);
    }

    @Override
    public @NotNull BukkitTask runTaskTimer(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) throws IllegalArgumentException {
        delay = Math.max(1L, delay);
        period = Math.max(1L, period);
        return FixedScheduledTask.setupSyncTask(globalSyncScheduler.runAtFixedRate(plugin, scheduledTask -> task.run(), delay, period));
    }

    @Override
    public void runTaskTimer(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay, long period) throws IllegalArgumentException {
        delay = Math.max(1L, delay);
        period = Math.max(1L, period);
        globalSyncScheduler.runAtFixedRate(plugin, scheduledTask -> task.accept(FixedScheduledTask.setupSyncTask(scheduledTask)), delay, period);
    }

    @Override
    public @NotNull BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) throws IllegalArgumentException {
        delay = Math.max(1L, delay);
        period = Math.max(1L, period);
        return FixedScheduledTask.setupAsyncTask(globalAsyncScheduler.runAtFixedRate(plugin, scheduledTask -> task.run(), delay, period, TimeUnit.MICROSECONDS));
    }

    @Override
    public void runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay, long period) throws IllegalArgumentException {
        delay = Math.max(1L, delay);
        period = Math.max(1L, period);
        globalAsyncScheduler.runAtFixedRate(plugin, scheduledTask -> task.accept(FixedScheduledTask.setupAsyncTask(scheduledTask)), delay, period, TimeUnit.MICROSECONDS);
    }

    @Deprecated
    @Override
    public boolean isCurrentlyRunning(int taskId) {
        return ((FixedScheduledTask) tasks.getElement(taskId)).task().getExecutionState() == ScheduledTask.ExecutionState.RUNNING;
    }

    @Deprecated
    @Override
    public boolean isQueued(int taskId) {
        return ((FixedScheduledTask) tasks.getElement(taskId)).task().getExecutionState() == ScheduledTask.ExecutionState.IDLE;
    }

    @Deprecated
    @Override
    public @NotNull List<BukkitWorker> getActiveWorkers() {
        return List.of();
    }

    @Deprecated
    @Override
    public @NotNull List<BukkitTask> getPendingTasks() {
        List<BukkitTask> tasks = new CopyOnWriteArrayList<>();

        for (BukkitTask task : FixedCraftScheduler.tasks) {
            if (((FixedScheduledTask) task).task().getExecutionState() == ScheduledTask.ExecutionState.IDLE) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    @Override
    public void mainThreadHeartbeat() {
        return;
    }

    @Override
    public @NotNull <T> Future<T> callSyncMethod(@NotNull Plugin plugin, @NotNull Callable<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Runnable runnable = toFuture(future, task);
        this.runTask(plugin, st -> runnable.run());
        return future;
    }
}
