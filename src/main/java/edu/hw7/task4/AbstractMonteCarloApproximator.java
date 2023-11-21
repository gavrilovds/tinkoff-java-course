package edu.hw7.task4;

public abstract class AbstractMonteCarloApproximator {

    protected static final int MONTE_CARLO_CONST = 4;

    public abstract double getPI(long simulations);

    protected boolean isInCircle(double x, double y) {
        return x * x + y * y < 1;
    }
}
