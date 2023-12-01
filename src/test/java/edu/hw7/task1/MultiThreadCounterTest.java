package edu.hw7.task1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class MultiThreadCounterTest {

    private static final int NUMBER_OF_THREADS = 10;

    @Test
    @DisplayName("#increment test")
    public void increment_shouldReturnCorrectValue() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);
        MultiThreadCounter testCounter = new MultiThreadCounter();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            executorService.submit(() -> {
                testCounter.increment();
                latch.countDown();
            });
        }
        latch.await();
        executorService.shutdown();
        int actual = testCounter.getCounterValue();
        assertThat(actual).isEqualTo(NUMBER_OF_THREADS);
    }
}
