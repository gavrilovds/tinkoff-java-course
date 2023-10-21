package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class InMemoryDictionary implements Dictionary {

    private final String[] words;

    public InMemoryDictionary(String[] words) {
        this.words = words;
    }

    @Override
    @NotNull
    public String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(0, words.length)];
    }
}
