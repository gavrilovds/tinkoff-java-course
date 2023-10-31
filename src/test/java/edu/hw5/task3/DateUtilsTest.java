package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class DateUtilsTest {

    private static Stream<Arguments> inputsForBasicTestParseDate() {
        return Stream.of(
            Arguments.of("2020-10-10", LocalDate.of(2020, 10, 10)),
            Arguments.of("2020-12-2", LocalDate.of(2020, 12, 2)),
            Arguments.of("1/3/1976", LocalDate.of(1976, 3, 1)),
            Arguments.of("1/3/20", LocalDate.of(2020, 3, 1)),
            Arguments.of("tomorrow", LocalDate.now().plusDays(1)),
            Arguments.of("today", LocalDate.now()),
            Arguments.of("yesterday", LocalDate.now().minusDays(1)),
            Arguments.of("1 day ago", LocalDate.now().minusDays(1)),
            Arguments.of("2234 days ago", LocalDate.now().minusDays(2234))
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForBasicTestParseDate")
    @DisplayName("#parseDate basic test")
    public void parseDate_shouldReturnParsedDateFirst(String testString, LocalDate expected) {
        Optional<LocalDate> actual = DateUtils.parseDate(testString);
        assertThat(actual.get()).isEqualTo(expected);
    }

    @Test
    @DisplayName("#parseDate invalid input test")
    public void parseDate_shouldReturnEmptyOptional_whenStringHasNotBeenParsed() {
        Optional<LocalDate> actual = DateUtils.parseDate("23213 dayyys ago");
        assertThat(actual.isEmpty()).isEqualTo(true);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#parseDate null and empty test")
    public void parseDate_shouldThrowExceptionWhenInputStringIsNullOrEmpty(String testString) {
        assertThatThrownBy(() -> DateUtils.parseDate(testString)).isInstanceOf(IllegalArgumentException.class);
    }
}
