package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class InMemoryDictionary implements Dictionary {

    private final String[] words = new String[] {"tinkoff", "java", "world"};

    @Override
    public @NotNull String randomWord() {
        Random random = new Random();
        return words[random.nextInt(0, words.length)];
    }
}
