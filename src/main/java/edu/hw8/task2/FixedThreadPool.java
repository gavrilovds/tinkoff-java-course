package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.NonNull;
import lombok.SneakyThrows;

public final class FixedThreadPool implements ThreadPool {

    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;

    private FixedThreadPool(Thread[] threads) {
        tasks = new LinkedBlockingQueue<>();
        this.threads = threads;
    }

    public static FixedThreadPool create(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("number of threads should be > 0");
        }
        return new FixedThreadPool(new Thread[numberOfThreads]);
    }

    @Override
    @SneakyThrows
    public void start() {
        boolean hasStarted = false;
        while (!hasStarted) {
            for (int i = 0; i < threads.length; i++) {
                if (threads[i] == null || !threads[i].isAlive()) {
                    threads[i] = new Thread(tasks.take());
                    threads[i].start();
                    hasStarted = true;
                    break;
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public void execute(@NonNull Runnable runnable) {
        tasks.put(runnable);
        start();
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
    }
}
