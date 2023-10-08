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
        "-11211230, true"
    })
    @DisplayName("Тестирование isPalindromeDescendant")
    public void number_shouldReturnTrue_whenSelfOrDescendantIsPalindrome(int testNumber, boolean expected) {
        boolean actual = PalindromeUtils.isPalindromeDescendant(testNumber);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "123, 321",
        "444, 444",
        "8374, 4738"
    })
    @DisplayName("Тестирование getReversedNumber")
    public void number_shouldReturnReversedNumber(int testNumber, int expected) {
        int actual = PalindromeUtils.getReversedNumber(testNumber);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "4321, 37",
        "11, 2",
        "321, 33",
        "7634, 713"
    })
    @DisplayName("Тестирование getDescendantNumber")
    public void number_shouldReturnNextNumberWhereTwoAdjacentDigitsAreAddedTogether(int testNumber, int expected) {
        int actual = PalindromeUtils.getDescendantNumber(testNumber);
        assertThat(actual).isEqualTo(expected);
    }
}
