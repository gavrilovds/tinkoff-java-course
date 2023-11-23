package edu.hw7.task3;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadWriteLockPersonDatabaseTest {

    @Test
    @DisplayName("general test")
    @SneakyThrows
    public void generalTest_shouldWorkCorrect(){
        PersonDatabase database = new ReadWriteLockPersonDatabase();
        Person person = new Person(1, "Dima", "Moscow", "49723842");
        var executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> {
            database.add(person);
        });
        Future<List<Person>>[] threadResults = new Future[3];
        threadResults[0] = executorService.submit(() -> database.findByName("Dima"));
        threadResults[1] = executorService.submit(() -> database.findByAddress("Moscow"));
        threadResults[2] = executorService.submit(() -> database.findByPhone("49723842"));
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        Assertions.assertAll(
            () -> assertThat(threadResults[0].get()).contains(person),
            () -> assertThat(threadResults[1].get()).contains(person),
            () -> assertThat(threadResults[2].get()).contains(person)
        );
    }
}
