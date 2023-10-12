package edu.project1;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class ConsoleHangmanTest {

    @Test
    @DisplayName("Game doesn`t start test")
    public void gameShouldThrowException_whenHiddenWordIsEmpty() {
        String[] words = new String[] {"", ""};
        assertThatThrownBy(() -> new ConsoleHangman(new Session(new InMemoryDictionary(words)))).isInstanceOf(
            IllegalStateException.class);
    }

    @Test
    @DisplayName("Game start test")
    public void gameShouldNotThrowExceptionAtStart_whenHiddenWordIsCorrect() {
        String[] words = new String[] {"tinkoff", "java"};
        assertThatCode(() -> new ConsoleHangman(new Session(new InMemoryDictionary(words)))).doesNotThrowAnyException();
    }
}
