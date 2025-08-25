package me.coderfrish.scheduler;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class LemonMintTask implements BukkitTask {
    private final ScheduledTask task;
    private final boolean sync;

    public LemonMintTask(ScheduledTask task, boolean sync) {
        this.task = task;
        this.sync = sync;
        LemonMintScheduler.tasks.add(this);
    }

    @Override
    public int getTaskId() {
        return LemonMintScheduler.tasks.getId(this);
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
        LemonMintScheduler.tasks.remove(this);
        task.cancel();
    }

    static LemonMintTask setupAsyncTask(ScheduledTask task) {
        return new LemonMintTask(task, false);
    }

    static LemonMintTask setupSyncTask(ScheduledTask task) {
        return new LemonMintTask(task, true);
    }
}
