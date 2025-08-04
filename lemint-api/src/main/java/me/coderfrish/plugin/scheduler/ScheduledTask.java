package me.coderfrish.plugin.scheduler;

import org.jetbrains.annotations.NotNull;

public interface ScheduledTask {
    boolean isRepeatingTask();

    @NotNull io.papermc.paper.threadedregions.scheduler.ScheduledTask.CancelledState cancel();

    @NotNull io.papermc.paper.threadedregions.scheduler.ScheduledTask.ExecutionState getExecutionState();

    boolean isCancelled();
}
