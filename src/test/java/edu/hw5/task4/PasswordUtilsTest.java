package edu.hw5.task4;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class PasswordUtilsTest {

    private static Stream<Arguments> inputsForBasicTestIsPasswordValid() {
        return Stream.of(
            Arguments.of("dadrq#", true),
            Arguments.of("ewrfa%", true),
            Arguments.of("wetibuqber", false),
            Arguments.of("~fkmwerf", true),
            Arguments.of("sufiuse!fkjwn", true),
            Arguments.of("jbkf@adjan", true),
            Arguments.of("hbfww$bh", true),
            Arguments.of("fkmkwer^", true),
            Arguments.of("fwrw&", true),
            Arguments.of("fwrw*rqwer", true),
            Arguments.of("fnwijrew|fwr", true)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForBasicTestIsPasswordValid")
    @DisplayName("#isPasswordValid basic inputs test")
    public void isPasswordValid_shouldReturnTrue_whenPasswordContainsRequiredSymbols(
        String testPassword,
        boolean expected
    ) {
        boolean actual = PasswordUtils.isPasswordValid(testPassword);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#isPasswordValid null and empty test")
    public void isPasswordValid_shouldThrowException_whenInputPasswordIsNullOrEmpty(String testPassword) {
        assertThatThrownBy(() -> PasswordUtils.isPasswordValid(testPassword)).isInstanceOf(IllegalArgumentException.class);
    }
}
