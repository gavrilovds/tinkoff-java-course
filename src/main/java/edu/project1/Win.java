package edu.project1;

import org.jetbrains.annotations.NotNull;

public record Win(char[] state, int attempt, int maxAttempts) implements GuessResult {

    @Override
    @NotNull
    public String message() {
        return "You won!";
    }
}
