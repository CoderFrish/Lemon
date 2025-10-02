package me.coderfrish.traium.utils.scheduler;

import net.minecraft.world.entity.Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityTickScheduler {
    private final Map<Entity, ScheduledTask> queue = new ConcurrentHashMap<>();

    public void tick() {
        for (ScheduledTask entry : queue.values()) {
            entry.accept();
        }
    }

    public boolean isQueue(Entity entity) {
        return queue.containsKey(entity);
    }

    public ScheduledTask schedule(Entity entity, Runnable runnable) {
        ScheduledTask scheduledTask = new ScheduledTask(runnable);
        queue.put(entity, scheduledTask);
        return scheduledTask;
    }

    public class ScheduledTask {
        private final Runnable runnable;

        ScheduledTask(Runnable runnable) {
            this.runnable = runnable;
        }

        public void accept() {
            this.runnable.run();
        }

        public void cancel() {
            EntityTickScheduler.this.queue.remove(this);
        }
    }
}
