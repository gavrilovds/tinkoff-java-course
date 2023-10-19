package edu.hw2.task1;

public record Constant(double value) implements Expression {

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
