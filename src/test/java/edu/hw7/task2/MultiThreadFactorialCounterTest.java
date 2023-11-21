package edu.hw7.task2;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class MultiThreadFactorialCounterTest {

    private static Stream<Arguments> inputsForGetFactorialTest() {
        return Stream.of(
            Arguments.of(4, 24),
            Arguments.of(5, 120),
            Arguments.of(6, 720),
            Arguments.of(10, 3628800)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForGetFactorialTest")
    @DisplayName("#getFactorial test")
    public void getFactorial_shouldReturnFactorialOfGivenNumber(int testNumber, int expected) {
        int actual = MultiThreadFactorialCounter.getFactorial(testNumber);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("#getFactorial invalid input test")
    public void getFactorial_shouldThrowException_whenGivenNumberIsInvalid() {
        assertThatThrownBy(() -> MultiThreadFactorialCounter.getFactorial(-5)).isInstanceOf(IllegalArgumentException.class);
    }
}
