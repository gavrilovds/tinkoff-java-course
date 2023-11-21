package edu.hw7.task4;

public class MonteCarloApproximator extends AbstractMonteCarloApproximator {

    @Override
    public double getPI(long simulations) {
        double circleCount = 0;
        for (int i = 0; i < simulations; i++) {
            double x = Math.random();
            double y = Math.random();
            if (isInCircle(x, y)) {
                circleCount++;
            }
        }
        return MONTE_CARLO_CONST * (circleCount / (double) simulations);
    }
}
