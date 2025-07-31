package me.coderfrish.scheduler.task;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import me.coderfrish.scheduler.LemonMintScheduler;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class LemonMintAsyncTask implements BukkitTask {
    private final ScheduledTask task;

    public LemonMintAsyncTask(ScheduledTask task) {
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
        return false;
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

    @Override
    public ScheduledTask.ExecutionState getState() {
        return task.getExecutionState();
    }
}
