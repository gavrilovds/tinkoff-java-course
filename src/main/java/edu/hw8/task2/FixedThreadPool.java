package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {

    private final BlockingQueue<Thread> threads = new LinkedBlockingQueue<>();

    public static FixedThreadPool create(int numberOfThreads) {
        return new FixedThreadPool();
    }

    @Override
    public void start() {

    }

    @Override
    public void execute(Runnable runnable) {

    }

    @Override
    public void close() throws Exception {

    }
}
