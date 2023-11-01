package edu.hw5.task5;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class CarNumberUtilsTest {

    private static Stream<Arguments> inputsForBasicTestIsCarNumberValid() {
        return Stream.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВЕ77", false),
            Arguments.of("А123ВЕ7777", false)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForBasicTestIsCarNumberValid")
    @DisplayName("#isCarNumberValid basic test")
    public void isCarNumberValid_shouldReturnTrue_whenCarNumberIsValid(String testCarNumber, boolean expected) {
        boolean actual = CarNumberUtils.isCarNumberValid(testCarNumber);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#isCarNumberValid null and empty test")
    public void isCarNumberValid_shouldThrowException_whenInputCarNumberIsNullOrEmpty(String testCarNumber) {
        assertThatThrownBy(() -> CarNumberUtils.isCarNumberValid(testCarNumber)).isInstanceOf(IllegalArgumentException.class);
    }
}
