package edu.hw11.task3;

public class FibCalculator {

    public int getFib(int n) {
        if (n < 2) {
            return n;
        }
        return getFib(n - 1) + getFib(n - 2);
    }
}
