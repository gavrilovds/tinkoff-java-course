package edu.hw1.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PairTest {

    @Test
    @DisplayName("Тестирование getFirst")
    public void pair_shouldReturnFirstValueInPair() {
        Pair<Integer, Integer> testPair = new Pair<>(3, 4);
        int actual = testPair.getFirst();
        assertThat(actual).isEqualTo(3);
    }

    @Test
    @DisplayName("Тестирование getSecond")
    public void pair_shouldReturnSecondValueInPair() {
        Pair<Integer, Integer> testPair = new Pair<>(3, 4);
        int actual = testPair.getSecond();
        assertThat(actual).isEqualTo(4);
    }

    @Test
    @DisplayName("Тестирование setFirst")
    public void value_shouldSetValueToFirstElementInPair() {
        Pair<Integer, Integer> pair = new Pair<>(3, 4);
        int value = 5;

        pair.setFirst(value);
        int actual = pair.getFirst();

        assertThat(actual).isEqualTo(value);
    }

    @Test
    @DisplayName("Тестирование setSecond")
    public void value_shouldSetValueToSecondElementInPair() {
        Pair<Integer, Integer> pair = new Pair<>(3, 4);
        int value = 5;

        pair.setSecond(value);
        int actual = pair.getSecond();

        assertThat(actual).isEqualTo(value);
    }

}
