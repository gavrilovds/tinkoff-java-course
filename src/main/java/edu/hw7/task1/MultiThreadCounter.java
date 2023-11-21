package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCounter {

    private final AtomicInteger counter;

    public MultiThreadCounter() {
        counter = new AtomicInteger();
    }

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCounterValue() {
        return counter.get();
    }
}
