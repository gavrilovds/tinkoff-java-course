package edu.hw2.task1;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionTest {

    private static Stream<Arguments> inputsForConstantTest() {
        return Stream.of(
            Arguments.of(new Constant(5), 5),
            Arguments.of(new Constant(-5), -5)
        );
    }

    private static Stream<Arguments> inputsForMultiplicationTest() {
        return Stream.of(
            Arguments.of(new Constant(5), new Constant(10), 50),
            Arguments.of(new Constant(-5), new Constant(-5), 25)
        );
    }

    private static Stream<Arguments> inputsForNegateTest() {
        return Stream.of(
            Arguments.of(new Constant(5), -5),
            Arguments.of(new Constant(-5), 5)
        );
    }

    private static Stream<Arguments> inputsForExponentTest() {
        return Stream.of(
            Arguments.of(new Constant(8), 3, 512),
            Arguments.of(new Constant(-5), 2, 25)
        );
    }

    private static Stream<Arguments> inputsForAdditionTest() {
        return Stream.of(
            Arguments.of(new Constant(8), new Constant(3), 11),
            Arguments.of(new Constant(-5), new Constant(3), -2)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForConstantTest")
    @DisplayName("Тестирование Constant#evaluate")
    public void constant_ShouldEvaluateItsValue(Expression testConstant, double expected) {
        assertThat(testConstant.evaluate()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("inputsForMultiplicationTest")
    @DisplayName("Тестирование Multiplication#evaluate")
    public void twoConstants_shouldReturnTheirMultiplication(
        Expression constant1,
        Expression constant2,
        double expected
    ) {
        Expression multiplicationActual = new Multiplication(constant1, constant2);
        assertThat(multiplicationActual.evaluate()).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("inputsForNegateTest")
    @DisplayName("Тестирование Negate#evaluate")
    public void expression_shouldReturnSameExpressionWithOppositeSign(Expression expression, double expected) {
        Expression negativeExpression = new Negate(expression);
        assertThat(negativeExpression.evaluate()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("inputsForExponentTest")
    @DisplayName("Тестирование Exponent#evaluate")
    public void expressionAndDegree_shouldReturnExpressionToGivenDegree(
        Expression expression,
        int degree,
        double expected
    ) {
        Expression exponent = new Exponent(expression, degree);
        assertThat(exponent.evaluate()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("inputsForAdditionTest")
    @DisplayName("Тестирование Addition#evaluate")
    public void twoExpressions_shouldReturnTheirSum(Expression expression1, Expression expression2, double expected) {
        Expression sum = new Addition(expression1, expression2);
        assertThat(sum.evaluate()).isEqualTo(expected);
    }
}
