package edu.hw1.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class DigitsUtilsTest {

    @ParameterizedTest
    @CsvSource({
        "4666, 4",
        "544, 3",
        "-544, 3",
        "0, 1"
    })
    @DisplayName("Тестирование метода countDigits")
    public void number_shouldReturnAmountOfDigitsInNumber(int testNumber, int expected) {
        int actual = DigitsUtils.countDigits(testNumber);
        assertThat(actual).isEqualTo(expected);
    }
}
