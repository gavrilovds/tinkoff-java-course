package edu.hw2.task1;

public record Exponent(Expression expression, double degree) implements Expression {

    @Override
    public double evaluate() {
        return Math.pow(expression.evaluate(), degree);
    }

    @Override public String toString() {
        return expression.evaluate() + " ^ " + degree + " = " + evaluate();
    }
}
