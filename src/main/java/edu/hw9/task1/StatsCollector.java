package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.SneakyThrows;

public class StatsCollector {

    private static final String DELIMITER = " : ";
    private final AtomicInteger metricsCounter = new AtomicInteger(0);
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    @Getter
    private final Map<String, Stat> stats = new ConcurrentHashMap<>();

    @SneakyThrows
    public void push(Metric metric) {
        metricsCounter.incrementAndGet();
        executorService.submit(() -> {
            Stat stat = getStat(metric.values());
            stats.put(metric.name(), stat);
            metricsCounter.decrementAndGet();
        });
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @SneakyThrows
    public void printStats() {
        // Не знаю, правильное ли решение так сделать.
        // Так как мы ведь не знаем, сколько потоков всего придёт для передачи данных
        while (metricsCounter.get() != 0) {

        }
        executorService.shutdown();
        for (var entry : stats.entrySet()) {
            System.out.println(entry.getKey() + DELIMITER + entry.getValue());
        }
    }

    private Stat getStat(double[] values) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double summ = 0;
        for (double value : values) {
            min = Math.min(min, value);
            max = Math.max(max, value);
            summ += value;
        }
        return new Stat(summ, summ / values.length, min, max);
    }

}
