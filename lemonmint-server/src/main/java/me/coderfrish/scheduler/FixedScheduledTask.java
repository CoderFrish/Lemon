package me.coderfrish.scheduler;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

class FixedScheduledTask implements BukkitTask {
    public final ScheduledTask task;
    private final boolean sync;

    FixedScheduledTask(ScheduledTask task, boolean sync) {
        this.task = task;
        this.sync = sync;
        FixedCraftScheduler.tasks.add(this);
    }

    @Override
    public int getTaskId() {
        return FixedCraftScheduler.tasks.getId(this);
    }

    @Override
    public @NotNull Plugin getOwner() {
        return task.getOwningPlugin();
    }

    @Override
    public boolean isSync() {
        return sync;
    }

    @Override
    public boolean isCancelled() {
        return task.isCancelled();
    }

    @ApiStatus.Internal
    ScheduledTask task() {
        return task;
    }

    @Override
    public void cancel() {
        task.cancel();
        FixedCraftScheduler.tasks.remove(this);
    }

    static BukkitTask setupSyncTask(ScheduledTask task) {
        return new FixedScheduledTask(task, true);
    }

    static BukkitTask setupAsyncTask(ScheduledTask task) {
        return new FixedScheduledTask(task, false);
    }
}
