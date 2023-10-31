package edu.hw5.task2;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.*;

public class YearUtilsTest {

    private static Stream<Arguments> inputsForBasicTestGetAllFridaysThe13thOfYear() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),
            Arguments.of(
                2024,
                List.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            )
        );
    }

    private static Stream<Arguments> inputsForBasicTestGetTheClosestNextFridayThe13th() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(1925, 1, 13),
                LocalDate.of(1925, 2, 13)
            ),
            Arguments.of(
                LocalDate.of(2024, 10, 1),
                LocalDate.of(2024, 12, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForBasicTestGetAllFridaysThe13thOfYear")
    @DisplayName("#getAllFridaysThe13thOfYear basic test")
    public void getAllFridaysThe13thOfYear_shouldReturnListOfAllFridaysThe13th(int testYear, List<LocalDate> expected) {
        List<LocalDate> actual = YearUtils.getAllFridaysThe13thOfYear(testYear);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("#getAllFridaysThe13thOfYear invalid input test")
    public void getAllFridaysThe13thOfYear_shouldThrowException_whenInputIsInvalid() {
        assertThatThrownBy(() -> YearUtils.getAllFridaysThe13thOfYear(-12)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("inputsForBasicTestGetTheClosestNextFridayThe13th")
    @DisplayName("#getTheClosestNextFridayThe13th basic test")
    public void getTheClosestNextFridayThe13th_shouldReturnClosestNextFridayThe13th(
        LocalDate testDate,
        LocalDate expected
    ) {
        LocalDate actual = YearUtils.getTheClosestNextFridayThe13th(testDate);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("#getTheClosestNextFridayThe13th null test")
    public void getTheClosestNextFridayThe13th_shouldThrowException_whenInputParameterIsNull(LocalDate testDate) {
        assertThatThrownBy(() -> YearUtils.getTheClosestNextFridayThe13th(testDate)).isInstanceOf(
            IllegalArgumentException.class);
    }
}
