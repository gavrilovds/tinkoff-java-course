package edu.hw5.task7_8;

import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class BinaryUtilsTest {

    private static Stream<Arguments> inputsForDoesSatisfyPatternTest() {
        return Stream.of(
            Arguments.of("01011", BinaryUtils.CONTAINS_AT_LEAST_3_SYMBOLS_AND_ZERO_IS_THIRD, true),
            Arguments.of("11111", BinaryUtils.CONTAINS_AT_LEAST_3_SYMBOLS_AND_ZERO_IS_THIRD, false),
            Arguments.of("00000", BinaryUtils.CONTAINS_AT_LEAST_3_SYMBOLS_AND_ZERO_IS_THIRD, true),
            Arguments.of("5402", BinaryUtils.CONTAINS_AT_LEAST_3_SYMBOLS_AND_ZERO_IS_THIRD, false),
            Arguments.of("01011", BinaryUtils.STARTS_AND_ENDS_WITH_SAME_SYMBOL, false),
            Arguments.of("11111", BinaryUtils.STARTS_AND_ENDS_WITH_SAME_SYMBOL, true),
            Arguments.of("00000", BinaryUtils.STARTS_AND_ENDS_WITH_SAME_SYMBOL, true),
            Arguments.of("5402", BinaryUtils.STARTS_AND_ENDS_WITH_SAME_SYMBOL, false),
            Arguments.of("01011", BinaryUtils.LENGTH_MORE_THAN_ONE_AND_LESS_THAN_FOUR, false),
            Arguments.of("111", BinaryUtils.LENGTH_MORE_THAN_ONE_AND_LESS_THAN_FOUR, true),
            Arguments.of("10", BinaryUtils.LENGTH_MORE_THAN_ONE_AND_LESS_THAN_FOUR, true),
            Arguments.of("5402", BinaryUtils.LENGTH_MORE_THAN_ONE_AND_LESS_THAN_FOUR, false),
            Arguments.of("01011", BinaryUtils.NO_CONSECUTIVE_ONES, false),
            Arguments.of("111", BinaryUtils.NO_CONSECUTIVE_ONES, false),
            Arguments.of("10", BinaryUtils.NO_CONSECUTIVE_ONES, true),
            Arguments.of("0000", BinaryUtils.NO_CONSECUTIVE_ONES, true),
            Arguments.of("000", BinaryUtils.NO_CONSECUTIVE_ONES, true),
            Arguments.of("54012", BinaryUtils.NO_CONSECUTIVE_ONES, false),
            Arguments.of("1", BinaryUtils.NO_CONSECUTIVE_ONES, true),
            Arguments.of("01011", BinaryUtils.AT_LEAST_TWO_ZEROS_AND_LESS_THAN_TWO_ONES, false),
            Arguments.of("111", BinaryUtils.AT_LEAST_TWO_ZEROS_AND_LESS_THAN_TWO_ONES, false),
            Arguments.of("100", BinaryUtils.AT_LEAST_TWO_ZEROS_AND_LESS_THAN_TWO_ONES, true),
            Arguments.of("0000", BinaryUtils.AT_LEAST_TWO_ZEROS_AND_LESS_THAN_TWO_ONES, true),
            Arguments.of("000", BinaryUtils.AT_LEAST_TWO_ZEROS_AND_LESS_THAN_TWO_ONES, true),
            Arguments.of("54012", BinaryUtils.AT_LEAST_TWO_ZEROS_AND_LESS_THAN_TWO_ONES, false),
            Arguments.of("1", BinaryUtils.AT_LEAST_TWO_ZEROS_AND_LESS_THAN_TWO_ONES, false),
            Arguments.of("11", BinaryUtils.NOT_TWO_OR_THREE_CONSECUTIVE_ONES, false),
            Arguments.of("111", BinaryUtils.NOT_TWO_OR_THREE_CONSECUTIVE_ONES, false),
            Arguments.of("100", BinaryUtils.NOT_TWO_OR_THREE_CONSECUTIVE_ONES, true),
            Arguments.of("0000", BinaryUtils.NOT_TWO_OR_THREE_CONSECUTIVE_ONES, true),
            Arguments.of("000", BinaryUtils.NOT_TWO_OR_THREE_CONSECUTIVE_ONES, true),
            Arguments.of("54012", BinaryUtils.NOT_TWO_OR_THREE_CONSECUTIVE_ONES, false),
            Arguments.of("1", BinaryUtils.NOT_TWO_OR_THREE_CONSECUTIVE_ONES, true),
            Arguments.of("11", BinaryUtils.ODD_LENGTH, false),
            Arguments.of("111", BinaryUtils.ODD_LENGTH, true),
            Arguments.of("100", BinaryUtils.ODD_LENGTH, true),
            Arguments.of("0000", BinaryUtils.ODD_LENGTH, false),
            Arguments.of("10101", BinaryUtils.ODD_LENGTH, true),
            Arguments.of("000", BinaryUtils.ODD_LENGTH, true),
            Arguments.of("54012", BinaryUtils.ODD_LENGTH, false),
            Arguments.of("1", BinaryUtils.ODD_LENGTH, true),
            Arguments.of("11", BinaryUtils.ALL_ODD_SYMBOLS_ONES, true),
            Arguments.of("111", BinaryUtils.ALL_ODD_SYMBOLS_ONES, true),
            Arguments.of("100", BinaryUtils.ALL_ODD_SYMBOLS_ONES, false),
            Arguments.of("0000", BinaryUtils.ALL_ODD_SYMBOLS_ONES, false),
            Arguments.of("10101", BinaryUtils.ALL_ODD_SYMBOLS_ONES, true),
            Arguments.of("000", BinaryUtils.ALL_ODD_SYMBOLS_ONES, false),
            Arguments.of("54012", BinaryUtils.ALL_ODD_SYMBOLS_ONES, false),
            Arguments.of("1", BinaryUtils.ALL_ODD_SYMBOLS_ONES, true),
            Arguments.of("11", BinaryUtils.COUNT_OF_ZEROS_MULTIPLE_OF_THREE, false),
            Arguments.of("111", BinaryUtils.COUNT_OF_ZEROS_MULTIPLE_OF_THREE, false),
            Arguments.of("100", BinaryUtils.COUNT_OF_ZEROS_MULTIPLE_OF_THREE, false),
            Arguments.of("000000", BinaryUtils.COUNT_OF_ZEROS_MULTIPLE_OF_THREE, true),
            Arguments.of("10101", BinaryUtils.COUNT_OF_ZEROS_MULTIPLE_OF_THREE, false),
            Arguments.of("000", BinaryUtils.COUNT_OF_ZEROS_MULTIPLE_OF_THREE, true),
            Arguments.of("54012", BinaryUtils.COUNT_OF_ZEROS_MULTIPLE_OF_THREE, false),
            Arguments.of("1", BinaryUtils.COUNT_OF_ZEROS_MULTIPLE_OF_THREE, false),
            Arguments.of("11", BinaryUtils.START_WITH_ZERO_AND_ODD_LENGTH_OR_START_WITH_ONE_AND_EVEN_LENGTH, true),
            Arguments.of("111", BinaryUtils.START_WITH_ZERO_AND_ODD_LENGTH_OR_START_WITH_ONE_AND_EVEN_LENGTH, false),
            Arguments.of("100", BinaryUtils.START_WITH_ZERO_AND_ODD_LENGTH_OR_START_WITH_ONE_AND_EVEN_LENGTH, false),
            Arguments.of("000000", BinaryUtils.START_WITH_ZERO_AND_ODD_LENGTH_OR_START_WITH_ONE_AND_EVEN_LENGTH, false),
            Arguments.of("10101", BinaryUtils.START_WITH_ZERO_AND_ODD_LENGTH_OR_START_WITH_ONE_AND_EVEN_LENGTH, false),
            Arguments.of("000", BinaryUtils.START_WITH_ZERO_AND_ODD_LENGTH_OR_START_WITH_ONE_AND_EVEN_LENGTH, true),
            Arguments.of("54012", BinaryUtils.START_WITH_ZERO_AND_ODD_LENGTH_OR_START_WITH_ONE_AND_EVEN_LENGTH, false),
            Arguments.of("1", BinaryUtils.START_WITH_ZERO_AND_ODD_LENGTH_OR_START_WITH_ONE_AND_EVEN_LENGTH, false)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForDoesSatisfyPatternTest")
    @DisplayName("#doesSatisfyPattern test")
    public void doesSatisfyPattern_shouldReturnTrue_whenGivenLineSatisfiesGivenPattern(
        String testLine,
        Pattern testPattern,
        boolean expected
    ) {
        boolean actual = BinaryUtils.doesSatisfyPattern(testLine, testPattern);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#doesSatisfyPattern null and empty test")
    public void doesSatisfyPattern_shouldReturnFalse_whenGivenLineIsNullOrEmpty(String testLine) {
        boolean actual =
            BinaryUtils.doesSatisfyPattern(testLine, BinaryUtils.AT_LEAST_TWO_ZEROS_AND_LESS_THAN_TWO_ONES);
        assertThat(actual).isFalse();
    }

}
