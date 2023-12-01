package edu.hw7.task4;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloApproximator extends AbstractMonteCarloApproximator {

    @Override
    public double getPI(long simulations) {
        double circleCount = 0;
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < simulations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (isInCircle(x, y)) {
                circleCount++;
            }
        }
        return MONTE_CARLO_CONST * (circleCount / (double) simulations);
    }
}
