package me.coderfrish.scheduler;

import io.github.classgraph.*;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

import static me.coderfrish.scheduler.FixedScheduledTask.setupAsyncTask;
import static me.coderfrish.scheduler.FixedScheduledTask.setupSyncTask;
import static me.coderfrish.utility.FutureUtility.toFuture;

public class FixedEntityScheduler {
    public static final Map<String, MappingInfo> ENTITY_SCHEDULE_METHOD_MAPPINGS = new ConcurrentHashMap<>();
    public static final Map<String, List<String>> ENTITY_INVOKE_LIST = new ConcurrentHashMap<>();
    private static final Set<String> ENTITY_CLASS = new CopyOnWriteArraySet<>();

    static {
        /* scan entity classes */
        ENTITY_CLASS.add("org.bukkit.entity.Entity");
        try (ScanResult scanResult = new ClassGraph().enableClassInfo().acceptPackages("org.bukkit.entity").scan()) {
            ClassInfoList entitySubclasses = scanResult.getClassesImplementing("org.bukkit.entity.Entity");

            for (ClassInfo classInfo : entitySubclasses) {
                ENTITY_CLASS.add(classInfo.getName());
            }
        }

        try (ScanResult scanResult = new ClassGraph().enableMethodInfo().enableClassInfo().acceptPackages("org.bukkit.event").scan()) {
            ClassInfoList entitySubclasses = scanResult.getSubclasses("org.bukkit.event.Event");

            for (ClassInfo classInfo : entitySubclasses) {
                String name = classInfo.getName().replace(".", "/");
                if (!ENTITY_INVOKE_LIST.containsKey(name)) {
                    List<String> methods = new ArrayList<>();
                    for (MethodInfo methodInfo : classInfo.getMethodInfo()) {
                        TypeSignature resultType = methodInfo.getTypeDescriptor().getResultType();

                        if (ENTITY_CLASS.contains(resultType.toString())) {
                            methods.add(methodInfo.getName());
                        }
                    }

                    if (!methods.isEmpty()) {
                        ENTITY_INVOKE_LIST.put(name, methods);
                    }
                }
            }
        }

        /* format - owner.name + descriptor */
        ENTITY_SCHEDULE_METHOD_MAPPINGS.put(
                "org/bukkit/scheduler/BukkitScheduler.runTask(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;)Lorg/bukkit/scheduler/BukkitTask;",
                new MappingInfo("me/coderfrish/scheduler/FixedEntityScheduler", "runTask", "(Lorg/bukkit/entity/Entity;Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;)Lorg/bukkit/scheduler/BukkitTask;")
        );

        ENTITY_SCHEDULE_METHOD_MAPPINGS.put(
                "org/bukkit/scheduler/BukkitScheduler.runTaskLater(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;J)Lorg/bukkit/scheduler/BukkitTask;",
                new MappingInfo("me/coderfrish/scheduler/FixedEntityScheduler", "runTaskLater", "(Lorg/bukkit/entity/Entity;Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;J)Lorg/bukkit/scheduler/BukkitTask;")
        );

        ENTITY_SCHEDULE_METHOD_MAPPINGS.put(
                "org/bukkit/scheduler/BukkitScheduler.runTaskTimer(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;",
                new MappingInfo("me/coderfrish/scheduler/FixedEntityScheduler", "runTaskTimer", "(Lorg/bukkit/entity/Entity;Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;")
        );

        ENTITY_SCHEDULE_METHOD_MAPPINGS.put(
                "org/bukkit/scheduler/BukkitScheduler.runTaskAsynchronously(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;",
                new MappingInfo("me/coderfrish/scheduler/FixedEntityScheduler", "runTaskAsynchronously", "(Lorg/bukkit/entity/Entity;Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;")
        );

        ENTITY_SCHEDULE_METHOD_MAPPINGS.put(
                "org/bukkit/scheduler/BukkitScheduler.runTaskLaterAsynchronously(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;",
                new MappingInfo("me/coderfrish/scheduler/FixedEntityScheduler", "runTaskLaterAsynchronously", "(Lorg/bukkit/entity/Entity;Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;")
        );

        ENTITY_SCHEDULE_METHOD_MAPPINGS.put(
                "org/bukkit/scheduler/BukkitScheduler.runTaskTimerAsynchronously(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;",
                new MappingInfo("me/coderfrish/scheduler/FixedEntityScheduler", "runTaskTimerAsynchronously", "(Lorg/bukkit/entity/Entity;Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;")
        );
    }

    public record MappingInfo(String owner, String name, String descriptor) {}

    public static BukkitTask runTask(Entity entity, Plugin plugin, Runnable runnable) {
        return setupSyncTask(entity.getScheduler().run(plugin, t -> runnable.run(), null));
    }

    public static BukkitTask runTaskLater(Entity entity, Plugin plugin, Runnable runnable, long delay) {
        delay = Math.max(1L, delay);
        return setupSyncTask(entity.getScheduler().runDelayed(plugin, t -> runnable.run(), null, delay));
    }

    public static BukkitTask runTaskTimer(Entity entity, Plugin plugin, Runnable runnable, long delay, long period) {
        delay = Math.max(1L, delay);
        period = Math.max(1L, period);
        return setupSyncTask(entity.getScheduler().runAtFixedRate(plugin, t -> runnable.run(), null, delay, period));
    }

    public static void runTask(Entity entity, Plugin plugin, Consumer<BukkitTask> consumer) {
        entity.getScheduler().run(plugin, t -> consumer.accept(setupSyncTask(t)), null);
    }

    public static void runTaskLater(Entity entity, Plugin plugin, Consumer<BukkitTask> consumer, long delay) {
        delay = Math.max(1L, delay);
        entity.getScheduler().runDelayed(plugin, t -> consumer.accept(setupSyncTask(t)), null, delay);
    }

    public static void runTaskTimer(Entity entity, Plugin plugin, Consumer<BukkitTask> consumer, long delay, long period) {
        delay = Math.max(1L, delay);
        period = Math.max(1L, period);
        entity.getScheduler().runAtFixedRate(plugin, t -> consumer.accept(setupSyncTask(t)), null, delay, period);
    }

    public static BukkitTask runTaskAsynchronously(Entity entity, Plugin plugin, Runnable runnable) {
        return setupAsyncTask(entity.getScheduler().run(plugin, (t) -> runnable.run(), null));
    }

    public static BukkitTask runTaskLaterAsynchronously(Entity entity, Plugin plugin, Runnable runnable, long delay) {
        delay = Math.max(1L, delay);
        return setupAsyncTask(entity.getScheduler().runDelayed(plugin, (t) -> runnable.run(), null, delay));
    }

    public static BukkitTask runTaskTimerAsynchronously(Entity entity, Plugin plugin, Runnable runnable, long delay, long period) {
        delay = Math.max(1L, delay);
        period = Math.max(1L, period);
        return setupAsyncTask(entity.getScheduler().runAtFixedRate(plugin, t -> runnable.run(), null, delay, period));
    }

    public static void runTaskAsynchronously(Entity entity, Plugin plugin, Consumer<BukkitTask> consumer) {
        entity.getScheduler().run(plugin, t -> consumer.accept(setupAsyncTask(t)), null);
    }

    public static void runTaskLaterAsynchronously(Entity entity, Plugin plugin, Consumer<BukkitTask> consumer, long delay) {
        delay = Math.max(1L, delay);
        entity.getScheduler().runDelayed(plugin, t -> consumer.accept(setupAsyncTask(t)), null, delay);
    }

    public static void runTaskTimerAsynchronously(Entity entity, Plugin plugin, Consumer<BukkitTask> consumer, long delay, long period) {
        delay = Math.max(1L, delay);
        period = Math.max(1L, period);
        entity.getScheduler().runAtFixedRate(plugin, t -> consumer.accept(setupAsyncTask(t)), null, delay, period);
    }

    public static <T> Future<T> callSyncMethod(Plugin plugin, Callable<T> task, Entity entity) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Runnable runnable = toFuture(future, task);
        runTask(entity, plugin, runnable);
        return future;
    }
}
