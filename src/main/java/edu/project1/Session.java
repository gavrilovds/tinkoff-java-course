package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class Session {

    private static final int MAX_ATTEMPTS = 5;
    private static final int MIN_WORD_LENGTH = 4;
    private static final char SURRENDER_SYMBOL = '\0';
    private static final char HIDDEN_LETTER_SYMBOL = '*';
    private final String answer;
    private final char[] userAnswer;
    private int attempts;
    private int guessedLettersCounter;
    private GameStatus gameStatus;

    public Session(Dictionary dictionary) {
        this.answer = dictionary.getRandomWord();
        if (this.answer.length() < MIN_WORD_LENGTH) {
            throw new IllegalStateException("Hidden word length should be >= 4");
        }
        this.userAnswer = initUserAnswer();
        this.gameStatus = GameStatus.RUNNING;
    }

    @NotNull
    public GuessResult guess(char guess) {
        if (guess != SURRENDER_SYMBOL && answer.indexOf(guess) != -1) {
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == guess) {
                    userAnswer[i] = answer.charAt(i);
                    guessedLettersCounter++;
                }
            }
            if (guessedLettersCounter == answer.length()) {
                gameStatus = GameStatus.STOPPED;
                return new Win(userAnswer, attempts, MAX_ATTEMPTS);
            }
            return new SuccessfulGuess(userAnswer, attempts, MAX_ATTEMPTS);
        }
        attempts++;
        if (attempts == MAX_ATTEMPTS || guess == SURRENDER_SYMBOL) {
            return giveUp();
        }
        return new FailedGuess(userAnswer, attempts, MAX_ATTEMPTS);
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @NotNull
    private GuessResult giveUp() {
        gameStatus = GameStatus.STOPPED;
        return new Defeat(answer.toCharArray(), attempts, MAX_ATTEMPTS);
    }

    private char[] initUserAnswer() {
        char[] userAns = new char[answer.length()];
        Arrays.fill(userAns, HIDDEN_LETTER_SYMBOL);
        return userAns;
    }
}
