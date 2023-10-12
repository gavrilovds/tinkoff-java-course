package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("Lose test")
    public void sessionGuessShouldReturnDefeatGuessResult_whenNUserIsOutOfMistakes() {
        Session session = new Session(new InMemoryDictionary(new String[] {"tinkoff"}));

        session.guess('a');
        session.guess('a');
        session.guess('a');
        session.guess('a');
        GuessResult actual = session.guess('a');

        assertThat(actual).isInstanceOf(Defeat.class);

    }

    @Test
    @DisplayName("Win test")
    public void sessionGuessShouldReturnWinGuessResult_whenNUserGuessedTheWord() {
        Session session = new Session(new InMemoryDictionary(new String[] {"word"}));

        session.guess('w');
        session.guess('o');
        session.guess('r');
        GuessResult actual = session.guess('d');

        assertThat(actual).isInstanceOf(Win.class);

    }

    @Test
    @DisplayName("Game state changing correctly when guess is successful")
    public void sessionStateShouldChangingCorrectly_whenUserHasSuccessfulGuess() {
        Session session = new Session(new InMemoryDictionary(new String[] {"word"}));
        GuessResult successfulGuess = session.guess('w');
        Assertions.assertAll(
            () -> assertThat(session.getAttempts()).isEqualTo(0),
            () -> assertThat(successfulGuess).isInstanceOf(SuccessfulGuess.class),
            () -> assertThat(session.getUserAnswer()).containsExactly('w', '*', '*', '*')
        );
    }

    @Test
    @DisplayName("Game state changing correctly when guess is successful")
    public void sessionStateShouldChangingCorrectly_whenUserHasFailedGuess() {
        Session session = new Session(new InMemoryDictionary(new String[] {"word"}));
        GuessResult failedGuess = session.guess('m');
        Assertions.assertAll(
            () -> assertThat(session.getAttempts()).isEqualTo(1),
            () -> assertThat(failedGuess).isInstanceOf(FailedGuess.class),
            () -> assertThat(session.getUserAnswer()).containsExactly('*', '*', '*', '*')
        );
    }

    @Test
    @DisplayName("Surrender test")
    public void shouldReturnDefeat_whenUserGaveUp() {
        Session session = new Session(new InMemoryDictionary(new String[] {"word"}));
        GuessResult actual = session.guess('\0');
        assertThat(actual).isInstanceOf(Defeat.class);
    }

}
