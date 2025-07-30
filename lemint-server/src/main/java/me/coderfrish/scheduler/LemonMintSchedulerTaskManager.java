package me.coderfrish.scheduler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LemonMintSchedulerTaskManager<T> {
    private static final int START_ID = 1;

    private final AtomicInteger nextId = new AtomicInteger(START_ID);
    private final Map<Integer, T> IdToElementTasks = new ConcurrentHashMap<>();
    private final Map<T, Integer> ElementToIdTasks = new ConcurrentHashMap<>();

    public void add(T element) {
        int id = nextId.getAndIncrement();
        IdToElementTasks.put(id, element);
        ElementToIdTasks.put(element, id);
    }

    public int getId(T element) {
        return ElementToIdTasks.get(element);
    }

    public T getElement(int id) {
        return IdToElementTasks.get(id);
    }

    public void remove(T element) {
        int id = getId(element);
        IdToElementTasks.remove(id);
        ElementToIdTasks.remove(element);
    }
}
