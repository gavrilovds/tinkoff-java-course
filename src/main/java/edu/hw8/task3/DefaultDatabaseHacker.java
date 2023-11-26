package edu.hw8.task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;

public class DefaultDatabaseHacker extends AbstractDatabaseHacker {

    private final Map<String, String> leakedDatabase;
    private final Map<String, String> hackedData;

    public DefaultDatabaseHacker(Map<String, String> leakedDatabase) {
        this.leakedDatabase = leakedDatabase;
        this.hackedData = new HashMap<>();
    }

    @Override
    public Map<String, String> hack() {
        while (!leakedDatabase.isEmpty()) {
            for (int wordLength = MIN_PASSWORD_LENGTH; wordLength <= MAX_PASSWORD_LENGTH; wordLength++) {
                generatePasswords(wordLength);
            }
        }
        return hackedData;
    }

    private void generatePasswords(int wordLength) {
        int[] index = new int[wordLength];
        Arrays.fill(index, 0);
        while (!leakedDatabase.isEmpty()) {

            StringBuilder word = new StringBuilder();
            for (Integer integer : index) {
                word.append(alfCharacters.get(integer));
            }

            String generatedPass = word.toString();
            processGeneratedPassword(generatedPass);

            for (int i = index.length - 1;  ; --i) {
                if (i < 0) {
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
        }
    }

    private boolean isPasswordInLeakedDatabase(String md5hex) {
        return leakedDatabase.containsKey(md5hex);
    }

    private void addToHackedData(String username, String password) {
        hackedData.put(username, password);
    }

    public static void main(String[] args) { // 0f5b25cd58319cde0e6e33715b66db4c dima
        Map<String, String> db = new HashMap<>();
        db.put("0f5b25cd58319cde0e6e33715b66db4c", "Ya");
        db.put("1b18e2ffe3a99ea9486ba69c02c72763", "hehe");
        db.put("27183aacdcb689968f322032550ad33d", "fm");
        DatabaseHacker hacker = new DefaultDatabaseHacker(db);
        System.out.println(hacker.hack());
    }
}
