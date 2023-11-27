package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class FibonacciWithCustomThreadPoolTest {

    @Test
    @SneakyThrows
    @DisplayName("basic test")
    public void getFib_shouldCalculateFibForDifferentNumbersInMultipleThreads() {
        ThreadPool threadPool = FixedThreadPool.create(4);
        List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55);
        final List<Integer> actual = new CopyOnWriteArrayList<>();
        for (int i = 0; i <= 10; i++) {
            final int cur = i;
            threadPool.execute(() -> {
                actual.add(Fib.getFib(cur));
            });
        }
        threadPool.close();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
