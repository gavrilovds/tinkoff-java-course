package edu.hw3.task3;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class DictionaryUtilsTest {

    private static Stream<Arguments> basicTestsInputs() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2))
        );
    }

    @ParameterizedTest
    @MethodSource("basicTestsInputs")
    @DisplayName("Basic tests for #getFrequencyDictionary")
    public void list_shouldReturnFrequencyDictionaryOfItsElements(List<?> testList, Map<?, ?> expected) {
        Map<Object, Integer> actual = DictionaryUtils.getFrequencyDictionary(testList);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null and empty test for #getFrequencyDictionary")
    public void list_shouldThrowException_whenListIsEmptyOrNull(List<?> testList) {
        assertThatThrownBy(() -> DictionaryUtils.getFrequencyDictionary(testList)).isInstanceOf(IllegalArgumentException.class);
    }
}
