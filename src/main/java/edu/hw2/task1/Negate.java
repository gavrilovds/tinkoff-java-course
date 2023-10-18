package edu.hw2.task1;

public record Negate(Expression expression) implements Expression {

    @Override
    public double evaluate() {
        return -expression.evaluate();
    }

    @Override public String toString() {
        return " - " + expression.evaluate();
    }
}
