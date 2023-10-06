package edu.hw1.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArraysUtilsTest {

    @Test
    @DisplayName("Тестирование isNested #1")
    public void twoArrays_shouldReturnTrue_whenFirstArrayCanBeNestedInSecond() {
        int[] testArray1 = new int[] {1, 2, 3, 4};
        int[] testArray2 = new int[] {0, 6};

        boolean actual = ArraysUtils.isNested(testArray1, testArray2);

        assertThat(actual).isEqualTo(true);

    }

    @Test
    @DisplayName("Тестирование isNested #2")
    public void twoArrays_shouldReturnFalse_whenFirstArrayCannotBeNestedInSecond() {
        int[] testArray1 = new int[] {1, 2, 3, 4};
        int[] testArray2 = new int[] {2, 3};

        boolean actual = ArraysUtils.isNested(testArray1, testArray2);

        assertThat(actual).isEqualTo(false);

    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Тестирование isNested, на пустых входных данных #1")
    public void twoArrays_shouldThrowException_whenFirstArrayIsNullOrEmpty(int[] testArray1) {
        int[] testArray2 = new int[] {2, 3};
        assertThatThrownBy(() ->
        {
            boolean actual = ArraysUtils.isNested(testArray1, testArray2);

        }).isInstanceOf(IllegalArgumentException.class);
    }
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Тестирование isNested, на пустых входных данных #2")
    public void twoArrays_shouldThrowException_whenSecondArrayIsNullOrEmpty(int[] testArray2) {
        int[] testArray1 = new int[] {2, 3};
        assertThatThrownBy(() ->
        {
            boolean actual = ArraysUtils.isNested(testArray1, testArray2);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
