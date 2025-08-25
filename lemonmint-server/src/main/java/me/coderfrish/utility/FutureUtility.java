package me.coderfrish.utility;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class FutureUtility {
    public static <T> Runnable toFuture(CompletableFuture<T> future, Callable<T> task) {
        return () -> {
            try {
                future.complete(task.call());
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        };
    }
}
