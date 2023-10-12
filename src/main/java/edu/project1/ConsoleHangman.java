package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {

    private final Session session;
    private final Logger logger = LogManager.getLogger();
    private final Scanner scanner;

    public ConsoleHangman(Session session) {
        this.session = session;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (!session.getGameStatus().equals(GameStatus.STOPPED)) {
            char userGuessedLetter = userInput();
            printState(tryGuess(userGuessedLetter));
        }
    }

    private GuessResult tryGuess(char input) {
        return session.guess(input);
    }

    private void printState(GuessResult guess) {
        logger.info(guess.message());
        logger.info("The word: " + new String(guess.state()));
    }

    private char userInput() {
        logger.info("Guess a letter: ");
        while (true) {
            if (!scanner.hasNextLine()) {
                return '\0';
            }
            String input = scanner.nextLine();
            if (input.length() != 1) {
                logger.info("You should input only 1 letter! Please, try again.");
            } else {
                return input.toLowerCase().charAt(0);
            }
        }
    }
}
