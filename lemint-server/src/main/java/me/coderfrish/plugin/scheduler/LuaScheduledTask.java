package me.coderfrish.plugin.scheduler;

import org.jetbrains.annotations.NotNull;

public class LuaScheduledTask implements ScheduledTask {
    private final io.papermc.paper.threadedregions.scheduler.ScheduledTask scheduledTask;

    public LuaScheduledTask(io.papermc.paper.threadedregions.scheduler.ScheduledTask scheduledTask) {
        this.scheduledTask = scheduledTask;
    }

    @Override
    public boolean isRepeatingTask() {
        return scheduledTask.isRepeatingTask();
    }

    @Override
    public @NotNull io.papermc.paper.threadedregions.scheduler.ScheduledTask.CancelledState cancel() {
        return scheduledTask.cancel();
    }

    @Override
    public @NotNull io.papermc.paper.threadedregions.scheduler.ScheduledTask.ExecutionState getExecutionState() {
        return scheduledTask.getExecutionState();
    }

    @Override
    public boolean isCancelled() {
        return scheduledTask.isCancelled();
    }
}
