package edu.hw5.task7_8;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class BinaryUtilsTest {

    private static Stream<Arguments> inputsForRegex1() {
        return Stream.of(
            Arguments.of("01011", true),
            Arguments.of("11111", false),
            Arguments.of("00000", true),
            Arguments.of("5402", false)
        );
    }

    private static Stream<Arguments> inputsForRegex2() {
        return Stream.of(
            Arguments.of("01011", false),
            Arguments.of("11111", true),
            Arguments.of("00000", true),
            Arguments.of("5402", false)
        );
    }

    private static Stream<Arguments> inputsForRegex3() {
        return Stream.of(
            Arguments.of("01011", false),
            Arguments.of("111", true),
            Arguments.of("10", true),
            Arguments.of("5402", false)
        );
    }

    private static Stream<Arguments> inputsForRegex4() {
        return Stream.of(
            Arguments.of("01011", false),
            Arguments.of("111", false),
            Arguments.of("10", true),
            Arguments.of("0000", true),
            Arguments.of("000", true),
            Arguments.of("54012", false),
            Arguments.of("1", true)
        );
    }

    private static Stream<Arguments> inputsForRegex5() {
        return Stream.of(
            Arguments.of("01011", false),
            Arguments.of("111", false),
            Arguments.of("100", true),
            Arguments.of("0000", true),
            Arguments.of("000", true),
            Arguments.of("54012", false),
            Arguments.of("1", false)
        );
    }

    private static Stream<Arguments> inputsForRegex6() {
        return Stream.of(
            Arguments.of("11", false),
            Arguments.of("111", false),
            Arguments.of("100", true),
            Arguments.of("0000", true),
            Arguments.of("000", true),
            Arguments.of("54012", false),
            Arguments.of("1", true)
        );
    }

    private static Stream<Arguments> inputsForRegex7() {
        return Stream.of(
            Arguments.of("11", true),
            Arguments.of("111", true),
            Arguments.of("100", false),
            Arguments.of("0000", false),
            Arguments.of("10101", true),
            Arguments.of("000", false),
            Arguments.of("54012", false),
            Arguments.of("1", true)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForRegex1")
    @DisplayName("#regex1 basic test")
    public void regex1_shouldReturnTrue_whenInputStringMatchesPattern(String testString, boolean expected) {
        boolean actual = BinaryUtils.regex1(testString);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#regex1 null and empty test")
    public void regex1_shouldThrowException_whenInputStringIsNullOrEmpty(String testString) {
        assertThatThrownBy(() -> BinaryUtils.regex1(testString)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("inputsForRegex2")
    @DisplayName("#regex2 basic test")
    public void regex2_shouldReturnTrue_whenInputStringMatchesPattern(String testString, boolean expected) {
        boolean actual = BinaryUtils.regex2(testString);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#regex2 null and empty test")
    public void regex2_shouldThrowException_whenInputStringIsNullOrEmpty(String testString) {
        assertThatThrownBy(() -> BinaryUtils.regex2(testString)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("inputsForRegex3")
    @DisplayName("#regex3 basic test")
    public void regex3_shouldReturnTrue_whenInputStringMatchesPattern(String testString, boolean expected) {
        boolean actual = BinaryUtils.regex3(testString);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#regex3 null and empty test")
    public void regex3_shouldThrowException_whenInputStringIsNullOrEmpty(String testString) {
        assertThatThrownBy(() -> BinaryUtils.regex3(testString)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("inputsForRegex4")
    @DisplayName("#regex4 basic test")
    public void regex4_shouldReturnTrue_whenInputStringMatchesPattern(String testString, boolean expected) {
        boolean actual = BinaryUtils.regex4(testString);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#regex4 null and empty test")
    public void regex4_shouldThrowException_whenInputStringIsNullOrEmpty(String testString) {
        assertThatThrownBy(() -> BinaryUtils.regex4(testString)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("inputsForRegex5")
    @DisplayName("#regex5 basic test")
    public void regex5_shouldReturnTrue_whenInputStringMatchesPattern(String testString, boolean expected) {
        boolean actual = BinaryUtils.regex5(testString);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#regex5 null and empty test")
    public void regex5_shouldThrowException_whenInputStringIsNullOrEmpty(String testString) {
        assertThatThrownBy(() -> BinaryUtils.regex5(testString)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("inputsForRegex6")
    @DisplayName("#regex6 basic test")
    public void regex6_shouldReturnTrue_whenInputStringMatchesPattern(String testString, boolean expected) {
        boolean actual = BinaryUtils.regex6(testString);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#regex6 null and empty test")
    public void regex6_shouldThrowException_whenInputStringIsNullOrEmpty(String testString) {
        assertThatThrownBy(() -> BinaryUtils.regex6(testString)).isInstanceOf(IllegalArgumentException.class);
    }
    @ParameterizedTest
    @MethodSource("inputsForRegex7")
    @DisplayName("#regex7 basic test")
    public void regex7_shouldReturnTrue_whenInputStringMatchesPattern(String testString, boolean expected) {
        boolean actual = BinaryUtils.regex7(testString);
        assertThat(actual).isEqualTo(expected);
    }
}
