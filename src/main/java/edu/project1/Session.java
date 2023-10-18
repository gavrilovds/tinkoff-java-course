package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class Session {

    private final String answer;
    private final char[] userAnswer;
    //5 is a default number of attempts
    private final int maxAttempts = 5;
    private static final int MIN_WORD_LENGTH = 4;
    private static final char SURRENDER_SYMBOL = '\0';
    private int attempts;

    private GameStatus gameStatus;

    public Session(Dictionary dictionary) {
        this.answer = dictionary.randomWord();
        if (this.answer.length() < MIN_WORD_LENGTH) {
            throw new IllegalStateException("Hidden word length should be >= 4");
        }
        this.userAnswer = new char[answer.length()];
        this.gameStatus = GameStatus.RUNNING;
        Arrays.fill(userAnswer, '*');
    }

    @NotNull public GuessResult guess(char guess) {
        if (guess != SURRENDER_SYMBOL && answer.indexOf(guess) != -1) {
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == guess) {
                    userAnswer[i] = answer.charAt(i);
                }
            }
            if (new String(userAnswer).equals(answer)) {
                gameStatus = GameStatus.STOPPED;
                return new Win(userAnswer, attempts, maxAttempts);
            }
            return new SuccessfulGuess(userAnswer, attempts, maxAttempts);
        }
        attempts++;
        if (attempts == maxAttempts || guess == SURRENDER_SYMBOL) {
            return giveUp();
        }
        return new FailedGuess(userAnswer, attempts, maxAttempts);
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @NotNull private GuessResult giveUp() {
        gameStatus = GameStatus.STOPPED;
        return new Defeat(answer.toCharArray(), attempts, maxAttempts);
    }

    public int getAttempts() {
        return attempts;
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }
}
