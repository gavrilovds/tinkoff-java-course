package edu.hw1.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class BitUtilsTest {

    @ParameterizedTest
    @CsvSource({
        "16, 1, 1",
        "17, 2, 6",
        "-2, 2, -1",
        "11, 1, 7",
        "13, -1, -1"
    })
    @DisplayName("Тестирование rotateLeft")
    public void numberShift_shouldReturnNewNumberWithShiftingInLeft_whenInputIsCorrect(
        int testNumber, int testShift, int expected
    ) {
        int actual = BitUtils.rotateLeft(testNumber, testShift);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "8, 1, 4",
        "-2, 2, -1",
        "9, 1, 12",
        "9, 2, 6",
        "8, -1, -1"
    })
    @DisplayName("Тестирование rotateRight")
    public void numberShift_shouldReturnNewNumberWithShiftInRight_whenInputIsCorrect(
        int testNumber, int testShift, int expected
    ) {
        int actual = BitUtils.rotateRight(testNumber, testShift);
        assertThat(actual).isEqualTo(expected);
    }
}
