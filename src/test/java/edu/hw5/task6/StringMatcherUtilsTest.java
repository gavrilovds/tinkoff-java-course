package edu.hw5.task6;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class StringMatcherUtilsTest {

    private static Stream<Arguments> inputsForBasicTestIsStringSubstringOf() {
        return Stream.of(
            Arguments.of("abc", "achfdbaabgabcaabg", true),
            Arguments.of("eer", "achfdbaabgabcaabg", false)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForBasicTestIsStringSubstringOf")
    @DisplayName("#isStringSubstringOf basic test")
    public void isStringSubstringOf_shouldReturnTrue_whenInputStringIsSubstringOfFullString(
        String testSubstring,
        String testFullString,
        boolean expected
    ) {
        boolean actual = StringMatcherUtils.isStringSubstringOf(testSubstring, testFullString);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#isStringSubstringOf substring parameter null or empty test")
    public void isStringSubstringOf_shouldThrowException_whenSubstringInputIsNullOrEmpty(String testSubstring) {
        assertThatThrownBy(() -> StringMatcherUtils.isStringSubstringOf(testSubstring, "fwkmrwe")).isInstanceOf(
            IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#isStringSubstringOf fullString parameter null or empty test")
    public void isStringSubstringOf_shouldThrowException_whenFullStringInputIsNullOrEmpty(String testFullString) {
        assertThatThrownBy(() -> StringMatcherUtils.isStringSubstringOf("fjnweroq", testFullString)).isInstanceOf(
            IllegalArgumentException.class);
    }
}
