package edu.hw7.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MonteCarloApproximatorTest {

    @Test
    @DisplayName("#getPI test")
    public void getPI_shouldReturnCorrectCalculatedPI() {
        double actual = new MonteCarloApproximator().getPI(1000000000L);
        assertThat(String.format("%.2f", actual)).isEqualTo(String.format("%.2f", Math.PI));
    }
}
