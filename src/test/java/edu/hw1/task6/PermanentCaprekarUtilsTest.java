package edu.hw1.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PermanentCaprekarUtilsTest {

    @ParameterizedTest
    @CsvSource({
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "-1234, -1",
        "10001, -1",
        "8888, -1"
    })
    @DisplayName("Тестирование countK")
    public void number_shouldReturnNumberOfStepsToGetACarperkarConst_whenInputIsCorrect(int testNumber, int expected) {
        int actual = PermanentCaprekarUtils.countK(testNumber);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест getDigits")
    public void number_shouldReturnArrayWithDigitsOfNumber() {
        int testNumber = 1034;
        int[] actual = PermanentCaprekarUtils.getDigits(testNumber);
        assertThat(actual).contains(1, 0, 3, 4);
    }

    @Test
    @DisplayName("Тест getLowerNumber")
    public void array_shouldReturnLowerNumberConsistsOfElement() {
        int[] testArray = new int[] {1, 2, 3, 4};
        int actual = PermanentCaprekarUtils.getLowerNumber(testArray);
        assertThat(actual).isEqualTo(1234);
    }

    @Test
    @DisplayName("Тест gitLargerNumber")
    public void array_shouldReturnLargerNumberConsistsOfElement() {
        int[] testArray = new int[] {1, 2, 3, 4};
        int actual = PermanentCaprekarUtils.getLargerNumber(testArray);
        assertThat(actual).isEqualTo(4321);
    }
}
