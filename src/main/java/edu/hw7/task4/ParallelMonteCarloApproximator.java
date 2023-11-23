package edu.hw7.task4;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;

public class ParallelMonteCarloApproximator extends AbstractMonteCarloApproximator {

    private static final int NUMBER_OF_THREADS = 8;

    @Override
    @SneakyThrows
    public double getPI(long simulations) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        Future<Long>[] threadResults = new Future[NUMBER_OF_THREADS];
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threadResults[i] = executorService.submit(() -> {
                long circleCount = getCircleCount(simulations);
                latch.countDown();
                return circleCount;
            });
        }
        double totalCircleCount = 0;
        executorService.shutdown();
        latch.await();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            totalCircleCount += threadResults[i].get();
        }
        return MONTE_CARLO_CONST * (totalCircleCount / simulations);
    }

    private long getCircleCount(long simulations) {
        long circleCount = 0;
        Random random = ThreadLocalRandom.current();
        for (int j = 0; j < simulations / NUMBER_OF_THREADS; j++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (isInCircle(x, y)) {
                circleCount++;
            }
        }
        return circleCount;
    }
}
