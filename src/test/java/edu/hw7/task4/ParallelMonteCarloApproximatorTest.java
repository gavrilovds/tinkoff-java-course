package edu.hw7.task4;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParallelMonteCarloApproximatorTest {
    @Test
    @DisplayName("#getPI test")
    public void getPI_shouldReturnCorrectCalculatedPI() {
        double actual = new ParallelMonteCarloApproximator().getPI(1000000000L);
        assertThat(String.format("%.2f", actual)).isEqualTo(String.format("%.2f", Math.PI));
    }
}
