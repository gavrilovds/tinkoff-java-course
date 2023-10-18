package edu.hw3.task4;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class RomanUtilsTest {

    private static Stream<Arguments> basicTestsInputs() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(3999, "MMMCMXCIX"),
            Arguments.of(9, "IX")
        );
    }

    @ParameterizedTest
    @MethodSource("basicTestsInputs")
    @DisplayName("Basic tests for #convertToRoman")
    public void number_shouldReturnConvertedToRomanNumber(int testNumber, String expected) {
        String actual = RomanUtils.convertToRoman(testNumber);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "0",
        "-1",
        "4500"
    })
    @DisplayName("Wrong input tests for #convertToRoman")
    public void number_shouldThrowException_whenInputIsIncorrect(int testNumber) {
        assertThatThrownBy(() -> RomanUtils.convertToRoman(testNumber));
    }
}
