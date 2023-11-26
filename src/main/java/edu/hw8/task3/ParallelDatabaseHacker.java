package edu.hw8.task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;

public class ParallelDatabaseHacker extends AbstractDatabaseHacker {

    private final ConcurrentMap<String, String> leakedDatabase;
    private final ConcurrentMap<String, String> hackedData;
    private final ExecutorService executor;
    private final CountDownLatch latch;

    public ParallelDatabaseHacker(Map<String, String> leakedDatabase) {
        this.leakedDatabase = new ConcurrentHashMap<>(leakedDatabase);
        this.hackedData = new ConcurrentHashMap<>();
        this.executor = Executors.newCachedThreadPool();
        this.latch = new CountDownLatch(leakedDatabase.size());
    }

    @Override
    @SneakyThrows
    public Map<String, String> hack() {
        for (int wordLength = MIN_PASSWORD_LENGTH; wordLength <= MAX_PASSWORD_LENGTH; wordLength++) {
            for (int i = 0; i < ALF.length(); i += 3) {
                final int wordLengthCopy = wordLength;
                final int startIndex = i;
                executor.execute(() -> generatePasswords(wordLengthCopy, startIndex));
            }
        }
        executor.shutdown();
        latch.await();
        return hackedData;
    }

    private void generatePasswords(int wordLength, int startIndex) {
        int[] index = new int[wordLength];
        Arrays.fill(index, startIndex);
        while (index[0] != (startIndex - 3) && !leakedDatabase.isEmpty()) {

            StringBuilder word = new StringBuilder();
            for (Integer integer : index) {
                word.append(alfCharacters.get(integer));
            }

            String generatedPass = word.toString();
            processGeneratedPassword(generatedPass);

            for (int i = index.length - 1; ; --i) {
                if (i < 0) {
                    System.out.println(
                        Thread.currentThread().getName() + " : start index = " + startIndex + " " + Arrays.toString(
                            index));
                    return;
                }
                index[i]++;
                if (index[i] == alfCharacters.size()) {
                    index[i] = 0;
                } else {
                    break;
                }
            }

        }
    }

    private void processGeneratedPassword(String generatedPassword) {
        String md5Pass = DigestUtils.md5Hex(generatedPassword);
        if (isPasswordInLeakedDatabase(md5Pass)) {
            addToHackedData(leakedDatabase.get(md5Pass), generatedPassword);
            leakedDatabase.remove(md5Pass);
            latch.countDown();
        }
    }

    private boolean isPasswordInLeakedDatabase(String md5hex) {
        return leakedDatabase.containsKey(md5hex);
    }

    private void addToHackedData(String username, String password) {
        hackedData.putIfAbsent(username, password);
    }

    public static void main(String[] args) { // 0f5b25cd58319cde0e6e33715b66db4c dima
        Map<String, String> db = new HashMap<>();
        db.put("0f5b25cd58319cde0e6e33715b66db4c", "Ya");
        db.put("1b18e2ffe3a99ea9486ba69c02c72763", "hehe");
        db.put("27183aacdcb689968f322032550ad33d", "fm");
        DatabaseHacker hacker = new ParallelDatabaseHacker(db);
        System.out.println(hacker.hack());
    }
}
