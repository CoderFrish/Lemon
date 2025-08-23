package me.coderfrish.scheduler;

import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ApiStatus.Internal
public class TransferTaskManager {
    private static final int START_ID = 1;

    private final AtomicInteger nextId = new AtomicInteger(START_ID);
    private final Map<Integer, BukkitTask> IdToElementTasks = new ConcurrentHashMap<>();
    private final Map<BukkitTask, Integer> ElementToIdTasks = new ConcurrentHashMap<>();

    public void push(BukkitTask element) {
        int id = nextId.getAndIncrement();
        IdToElementTasks.put(id, element);
        ElementToIdTasks.put(element, id);
    }

    public int getId(BukkitTask element) {
        return ElementToIdTasks.get(element);
    }

    public BukkitTask getElement(int id) {
        return IdToElementTasks.get(id);
    }

    public void pop(BukkitTask element) {
        int id = getId(element);
        IdToElementTasks.remove(id);
        ElementToIdTasks.remove(element);
    }
}
