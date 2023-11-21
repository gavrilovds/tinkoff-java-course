package edu.hw7.task3;

import java.security.MessageDigest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SynchronizedPersonDatabaseTest {

    @Test
    @DisplayName("general test")
    public void generalTest_shouldWorkCorrect() throws InterruptedException {
        PersonDatabase database = new SynchronizedPersonDatabase();
        Thread thread1 = new Thread(() -> {
            database.add(new Person(1, "dima", "fewfw", "34949324"));
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("NAME : " + database.findByName("dima"));
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("ADDRESS : " + database.findByAddress("fewfw"));
        });
        Thread thread4 = new Thread(() -> {
            System.out.println("PHONE : " + database.findByPhone("34949324"));
        });
        thread1.start();
        Thread.sleep(1);
        thread4.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }
}
