package net.minepos.pterobot.tasks;

import com.google.inject.Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class Task {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(10);

    public static void async(final Consumer<GRunnable> task) {
        EXECUTOR.submit(runnable(task));
    }

    public static void async(GRunnable task) {
        EXECUTOR.submit(task);
    }

    private static GRunnable runnable(final Consumer<GRunnable> task) {
        return new GRunnable() {
            @Override
            public void run() {
                task.accept(this);
            }
        };
    }

    public static void shutdown() {
        EXECUTOR.shutdownNow();
        SCHEDULER.shutdownNow();
    }
}
