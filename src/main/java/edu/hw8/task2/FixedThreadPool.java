package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.SneakyThrows;

public final class FixedThreadPool implements ThreadPool {

    private final BlockingQueue<Runnable> tasks;
    private final Worker[] threads;
    private final AtomicBoolean isShutdown;

    private FixedThreadPool(Worker[] threads) {
        this.threads = threads;
        this.tasks = new LinkedBlockingQueue<>();
        this.isShutdown = new AtomicBoolean(false);
        start();
    }

    public static FixedThreadPool create(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("number of threads should be > 0");
        }
        return new FixedThreadPool(new Worker[numberOfThreads]);
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Worker();
            threads[i].start();
        }
    }

    @Override
    @SneakyThrows
    public void execute(Runnable runnable) {
        if (!isShutdown.get()) {
            tasks.put(runnable);
        }
    }

    @Override
    public void close() {
        isShutdown.set(true);
    }

    private final class Worker extends Thread {

        @Override
        @SneakyThrows
        public void run() {
            while (!isShutdown.get() || !tasks.isEmpty()) {
                Runnable runnable;
                while ((runnable = tasks.poll()) != null) {
                    runnable.run();
                }
            }
        }
    }
}
