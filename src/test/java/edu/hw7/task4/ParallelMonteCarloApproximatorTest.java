package edu.hw7.task4;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParallelMonteCarloApproximatorTest {
    @Test
    @DisplayName("#getPI test")
    public void getPI_shouldReturnCorrectCalculatedPI() {
        double actual = new ParallelMonteCarloApproximator().getPI(1000000000L);
        assertThat(actual).isCloseTo(Math.PI, Offset.offset(0.01));
    }
}
