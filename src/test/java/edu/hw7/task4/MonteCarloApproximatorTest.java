package edu.hw7.task4;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MonteCarloApproximatorTest {

    @Test
    @DisplayName("#getPI test")
    public void getPI_shouldReturnCorrectCalculatedPI() {
        double actual = new MonteCarloApproximator().getPI(10000000L);
        assertThat(actual).isCloseTo(Math.PI, Offset.offset(0.01));

    }
}
