package me.coderfrish.scheduler.task;

import me.coderfrish.scheduler.LemonMintScheduledTask;
import me.coderfrish.scheduler.LemonMintScheduler;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class LemonMintTask implements BukkitTask {
    private final LemonMintScheduledTask task;

    public LemonMintTask(LemonMintScheduledTask task) {
        this.task = task;
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
}
