package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Session session;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleHangman(Dictionary dictionary) {
        session = new Session(dictionary);
    }

    public void run() {
        while (!session.getGameStatus().equals(GameStatus.STOPPED)) {
            char userGuessedLetter = getUserInput();
            printState(tryGuess(userGuessedLetter));
        }
    }

    private GuessResult tryGuess(char input) {
        return session.guess(input);
    }

    private void printState(GuessResult guess) {
        LOGGER.info(guess.message());
        LOGGER.info("The word: " + new String(guess.state()));
    }

    private char getUserInput() {
        LOGGER.info("Guess a letter: ");
        while (true) {
            if (!scanner.hasNextLine()) {
                return '\0';
            }
            String input = scanner.nextLine();
            if (input.length() != 1) {
                LOGGER.info("You should input only 1 letter! Please, try again.");
            } else {
                return input.toLowerCase().charAt(0);
            }
        }
    }
}
