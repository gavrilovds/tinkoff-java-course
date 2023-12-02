package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.SneakyThrows;

public class StatsCollector {

    private static final String DELIMITER = " : ";
    private static final int NUMBER_OF_THREADS = 5;
    private final AtomicInteger metricsCounter = new AtomicInteger(0);
    private final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    @Getter
    private final Map<String, Stat> stats = new ConcurrentHashMap<>();
    private final BlockingQueue<Metric> metricsToProcess = new LinkedBlockingQueue<>();

    public StatsCollector() {
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            executorService.submit(this::processMetrics);
        }
    }

    @SneakyThrows
    public void push(Metric metric) {
        metricsCounter.incrementAndGet();
        metricsToProcess.put(metric);
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @SneakyThrows
    public void printStats() {
        // Не знаю, правильное ли решение так сделать.
        // Так как мы ведь не знаем, сколько потоков всего придёт для передачи данных
        executorService.shutdown();
        while (metricsCounter.get() != 0) {
            // ожидаем, пока все поступившие метрики обработаются
        }
        for (var entry : stats.entrySet()) {
            System.out.println(entry.getKey() + DELIMITER + entry.getValue());
        }
    }

    @SneakyThrows
    private void processMetrics() {
        while (!executorService.isShutdown()) {
            Metric currentMetric = metricsToProcess.take();
            Stat stat = getStat(currentMetric.values());
            stats.put(currentMetric.name(), stat);
            metricsCounter.decrementAndGet();
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
