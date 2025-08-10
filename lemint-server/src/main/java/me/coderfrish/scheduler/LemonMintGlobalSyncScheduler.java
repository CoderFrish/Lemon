package me.coderfrish.scheduler;

import ca.spottedleaf.concurrentutil.util.Validate;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class LemonMintGlobalSyncScheduler {
    private static final List<ScheduledTask> tasks = new CopyOnWriteArrayList<>();
    private static final PriorityQueue<ScheduledTask> taskQueue = new PriorityQueue<>();

    public void tick() {
        long now = System.currentTimeMillis();

        synchronized (this) {
            while (!taskQueue.isEmpty() && taskQueue.peek().nextRun <= now) {
                ScheduledTask task = taskQueue.poll();

                if (!task.cancelled) {
                    task.run();

                    if (task.period > 0) {
                        task.nextRun += task.period;
                        taskQueue.add(task);
                    }
                }
            }
        }
    }

    public LemonMintScheduledTask runTask(Plugin plugin , Consumer<LemonMintScheduledTask> task) {
        return schedule(plugin, task, 0, 0);
    }

    public LemonMintScheduledTask runTaskLater(Plugin plugin, Consumer<LemonMintScheduledTask> task, long delayMs) {
        return schedule(plugin, task, delayMs, 0);
    }

    public LemonMintScheduledTask runTaskTimer(Plugin plugin, Consumer<LemonMintScheduledTask> task, long delayMs, long periodMs) {
        return schedule(plugin, task, delayMs, periodMs);
    }

    private LemonMintScheduledTask schedule(Plugin plugin, Consumer<LemonMintScheduledTask> runnable, long delay, long period) {
        ScheduledTask task = new ScheduledTask(
                plugin,
                runnable,
                System.currentTimeMillis() + delay,
                period
        );

        synchronized (this) {
            tasks.add(task);
            taskQueue.add(task);
        }

        return task;
    }

    public void cancelTasks(Plugin plugin) {
        Validate.notNull(plugin, "Plugin may not be null");

        final List<ScheduledTask> toCancel = new ArrayList<>();
        synchronized (this) {
            for (ScheduledTask task : tasks) {
                if (task.plugin == plugin) {
                    toCancel.add(task);
                }
            }
        }

        for (ScheduledTask scheduledTask : toCancel) {
            scheduledTask.cancel();
        }
    }

    private static class ScheduledTask implements LemonMintScheduledTask, Runnable, Comparable<ScheduledTask>  {
        final Consumer<LemonMintScheduledTask> runnable;
        final Plugin plugin;
        long nextRun;
        final long period;
        boolean cancelled = false;

        ScheduledTask(Plugin plugin, Consumer<LemonMintScheduledTask> runnable, long nextRun, long period) {
            this.plugin = plugin;
            this.runnable = runnable;
            this.nextRun = nextRun;
            this.period = period;
        }

        @Override
        public void cancel() {
            cancelled = true;
            taskQueue.remove(this);
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public Plugin getOwningPlugin() {
            return plugin;
        }

        @Override
        public void run() {
            this.runnable.accept(this);
        }

        @Override
        public int compareTo(ScheduledTask other) {
            return Long.compare(this.nextRun, other.nextRun);
        }
    }
}
