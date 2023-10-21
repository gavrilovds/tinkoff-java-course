package edu.project1;

import org.jetbrains.annotations.NotNull;

public record Defeat(char[] state, int attempt, int maxAttempts) implements GuessResult {

    @Override
    @NotNull
    public String message() {
        return "You lost!";
    }
}
