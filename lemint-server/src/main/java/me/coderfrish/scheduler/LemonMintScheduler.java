package me.coderfrish.scheduler;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import me.coderfrish.scheduler.task.LemonMintAsyncTask;
import me.coderfrish.scheduler.task.LemonMintTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class LemonMintScheduler implements BukkitScheduler {
    public static final LemonMintSchedulerTaskManager<BukkitTask> tasks = new LemonMintSchedulerTaskManager<>();

    @Override
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay) {
        return this.scheduleSyncRepeatingTask(plugin, task, delay, 0L);
    }

    @Override
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task) {
        return this.scheduleSyncDelayedTask(plugin, task, 0L);
    }

    @Override
    public int scheduleSyncRepeatingTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) {
        return this.runTaskTimer(plugin, task, delay, period).getTaskId();
    }

    @Override
    public int scheduleAsyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay) {
        return this.scheduleAsyncRepeatingTask(plugin, task, delay, 0L);
    }

    @Override
    public int scheduleAsyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task) {
        return this.scheduleAsyncDelayedTask(plugin, task, 0L);
    }

    @Override
    public int scheduleAsyncRepeatingTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) {
        return this.runTaskTimerAsynchronously(plugin, task, delay, period).getTaskId();
    }

    @Override
    public void cancelTask(int taskId) {
        LemonMintScheduler.tasks.getElement(taskId).cancel();
    }

    @Override
    public void cancelTasks(@NotNull Plugin plugin) {
        Bukkit.getGlobalRegionScheduler().cancelTasks(plugin);
    }

    @Override
    public @NotNull BukkitTask runTask(@NotNull Plugin plugin, @NotNull Runnable task) throws IllegalArgumentException {
        return new LemonMintTask(Bukkit.getGlobalRegionScheduler().run(plugin, scheduledTask -> task.run()));
    }

    @Override
    public void runTask(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task) throws IllegalArgumentException {
        Bukkit.getGlobalRegionScheduler().run(plugin, scheduledTask -> task.accept(new LemonMintTask(scheduledTask)));
    }

    @Override
    public @NotNull BukkitTask runTaskAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task) throws IllegalArgumentException {
        return new LemonMintAsyncTask(Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> task.run()));
    }

    @Override
    public void runTaskAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task) throws IllegalArgumentException {
        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> task.accept(new LemonMintAsyncTask(scheduledTask)));
    }

    @Override
    public @NotNull BukkitTask runTaskLater(@NotNull Plugin plugin, @NotNull Runnable task, long delay) throws IllegalArgumentException {
        return new LemonMintTask(Bukkit.getGlobalRegionScheduler().runDelayed(plugin, scheduledTask -> task.run(), delay));
    }

    @Override
    public void runTaskLater(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay) throws IllegalArgumentException {
        Bukkit.getGlobalRegionScheduler().runDelayed(plugin, scheduledTask -> task.accept(new LemonMintTask(scheduledTask)), delay);
    }

    @Override
    public @NotNull BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay) throws IllegalArgumentException {
        return new LemonMintAsyncTask(Bukkit.getAsyncScheduler().runDelayed(plugin, scheduledTask -> task.run(), delay * 50, TimeUnit.MILLISECONDS));
    }

    @Override
    public void runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay) throws IllegalArgumentException {
        Bukkit.getAsyncScheduler().runDelayed(plugin, scheduledTask -> task.accept(new LemonMintAsyncTask(scheduledTask)), delay * 50, TimeUnit.MILLISECONDS);
    }

    @Override
    public @NotNull BukkitTask runTaskTimer(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) throws IllegalArgumentException {
        return new LemonMintTask(Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, scheduledTask -> task.run(), delay, period));
    }

    @Override
    public void runTaskTimer(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay, long period) throws IllegalArgumentException {
        Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, scheduledTask -> task.accept(new LemonMintTask(scheduledTask)), delay, period);
    }

    @Override
    public @NotNull BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) throws IllegalArgumentException {
        return new LemonMintAsyncTask(Bukkit.getAsyncScheduler().runAtFixedRate(plugin, scheduledTask -> task.run(), delay * 50, period * 50, TimeUnit.MILLISECONDS));
    }

    @Override
    public void runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay, long period) throws IllegalArgumentException {
        Bukkit.getAsyncScheduler().runAtFixedRate(plugin, scheduledTask -> task.accept(new LemonMintAsyncTask(scheduledTask)), delay * 50, period * 50, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isCurrentlyRunning(int taskId) {
        return tasks.getElement(taskId).getState() == ScheduledTask.ExecutionState.RUNNING;
    }

    @Override
    public boolean isQueued(int taskId) {
        return tasks.getElement(taskId).getState() == ScheduledTask.ExecutionState.IDLE;
    }

    @Deprecated
    @Override
    public @NotNull List<BukkitWorker> getActiveWorkers() {
        throw new UnsupportedOperationException("Folia server isn`t supported yet.");
    }

    @Deprecated
    @Override
    public @NotNull List<BukkitTask> getPendingTasks() {
        throw new UnsupportedOperationException("Folia server isn`t supported yet.");
    }

    @Override
    public @NotNull Executor getMainThreadExecutor(@NotNull Plugin plugin) {
        throw new UnsupportedOperationException("Folia server isn`t supported yet.");
    }

    @Deprecated
    @Override
    public void mainThreadHeartbeat() {
        throw new UnsupportedOperationException("Folia server isn`t supported yet.");
    }

    @Deprecated
    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task, long delay) {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskLater(Plugin, long)");
    }

    @Deprecated
    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task) {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTask(Plugin)");
    }

    @Deprecated
    @Override
    public int scheduleSyncRepeatingTask(Plugin plugin, BukkitRunnable task, long delay, long period) {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskTimer(Plugin, long, long)");
    }

    @Deprecated
    @Override
    public BukkitTask runTask(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTask(Plugin)");
    }

    @Deprecated
    @Override
    public BukkitTask runTaskAsynchronously(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskAsynchronously(Plugin)");
    }

    @Deprecated
    @Override
    public BukkitTask runTaskLater(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskLater(Plugin, long)");
    }

    @Deprecated
    @Override
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskLaterAsynchronously(Plugin, long)");
    }

    @Deprecated
    @Override
    public BukkitTask runTaskTimer(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskTimer(Plugin, long, long)");
    }

    @Deprecated
    @Override
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Use BukkitRunnable#runTaskTimerAsynchronously(Plugin, long, long)");
    }

    @Deprecated
    @Override
    public @NotNull <T> Future<T> callSyncMethod(@NotNull Plugin plugin, @NotNull Callable<T> task) {
        throw new UnsupportedOperationException("Folia server isn`t supported yet.");
    }
}
