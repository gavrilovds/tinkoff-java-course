package edu.hw7.task2;

import java.util.stream.IntStream;

public final class MultiThreadFactorialCounter {

    private MultiThreadFactorialCounter() {
    }

    public static int getFactorial(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("num should be > 0");
        }
        return IntStream.rangeClosed(1, num)
            .parallel()
            .reduce(1, (a, b) -> a * b);
    }
}
