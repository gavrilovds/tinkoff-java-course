package edu.hw7.task3;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadWriteLockPersonDatabaseTest {

    @Test
    @DisplayName("general test")
    public void generalTest_shouldWorkCorrect() throws InterruptedException {
        PersonDatabase database = new ReadWriteLockPersonDatabase();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            Person person = new Person(i, "Name: " + i, "Address: " + i, "Number: " + i);
            executorService.submit(() -> {
                database.add(person);
            });
        }
        for (int i = 99; i >= 0; i--) {
            String name = "Name: " + i;
            String address = "Address: " + i;
            String number = "Number: " + i;
            executorService.submit(() -> {
                List<Person> byName = database.findByName(name);
                List<Person> byAdd = database.findByAddress(address);
                List<Person> byNum = database.findByPhone(number);
                System.out.println(byName + " " + byAdd + " " + byNum);
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

    }
}
