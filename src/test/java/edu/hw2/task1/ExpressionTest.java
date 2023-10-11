package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionTest {

    @Test
    @DisplayName("Тестирование Constant#evaluate")
    public void constant_ShouldEvaluateItsValue() {
        Expression constant = new Constant(5);
        assertThat(constant.evaluate()).isEqualTo(5);
    }

    @Test
    @DisplayName("Тестирование Multiplication#evaluate")
    public void twoConstants_shouldReturnTheirMultiplication() {
        Expression constant1 = new Constant(5);
        Expression constant2 = new Constant(10);
        Expression multiplicationActual = new Multiplication(constant1, constant2);
        assertThat(multiplicationActual.evaluate()).isEqualTo(50);
    }

    @Test
    @DisplayName("Тестирование Negate#evaluate")
    public void expression_shouldReturnSameExpressionWithOppositeSign() {
        Expression constant = new Constant(10);
        Expression negativeConstant = new Negate(constant);
        assertThat(negativeConstant.evaluate()).isEqualTo(-10);
    }

    @Test
    @DisplayName("Тестирование Exponent#evaluate")
    public void expressionAndDegree_shouldReturnExpressionToGivenDegree() {
        Expression constant = new Constant(8);
        Expression exponent = new Exponent(constant, 3);
        assertThat(exponent.evaluate()).isEqualTo(512);
    }

    @Test
    @DisplayName("Тестирование Addition#evaluate")
    public void twoExpression_shouldReturnTheirSum() {
        Expression constant1 = new Constant(13);
        Expression constant2 = new Constant(20);
        Expression sum = new Addition(constant1, constant2);
        assertThat(sum.evaluate()).isEqualTo(33);
    }

    @Test
    @DisplayName("Тестирование вложенных выражений")
    public void generalTest() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);

        var res = new Addition(exp, new Constant(1));

        assertThat(res.evaluate()).isEqualTo(37);
    }
}
