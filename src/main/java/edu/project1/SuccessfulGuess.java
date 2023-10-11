package edu.project1;

import org.jetbrains.annotations.NotNull;

public record SuccessfulGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {

    @Override
    public @NotNull String message() {
        return "Hit!";
    }
}
