package edu.hw2.task1;

public record Multiplication(Expression expression1, Expression expression2) implements Expression {

    @Override
    public double evaluate() {
        return expression1.evaluate() * expression2.evaluate();
    }

    @Override
    public String toString() {
        return "(" + expression1 + " * " + expression2 + ")";
    }
}
