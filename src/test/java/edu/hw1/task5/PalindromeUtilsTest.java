package edu.hw1.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeUtilsTest {

    @ParameterizedTest
    @CsvSource({
        "11211230,true",
        "13001120,true",
        "23336014,true",
        "11,true",
        "1,false",
        "238236,false",
        "-11211230, false"
    })
    @DisplayName("Тестирование isPalindromeDescendant")
    public void number_shouldReturnTrue_whenSelfOrDescendantIsPalindrome(int testNumber, boolean expected) {
        boolean actual = PalindromeUtils.isPalindromeDescendant(testNumber);
        assertThat(actual).isEqualTo(expected);
    }
}
