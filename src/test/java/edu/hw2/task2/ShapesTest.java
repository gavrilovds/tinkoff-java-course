package edu.hw2.task2;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ShapesTest {

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Test from task")
    public void rectangleArea(Rectangle rect) {
        Rectangle rectangle = rect.setWidth(20);
        Rectangle actual = rectangle.setHeight(10);
        assertThat(actual.area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Creating immutable square from other")
    public void shouldCreateSquareWithGivenSide() {
        Square actual = new Square(5).setSide(3);
        assertThat(actual.area()).isEqualTo(9);
    }

    @Test
    @DisplayName("Creating immutable rectangle from other")
    public void shouldCreateRectangleWithGivenHeightAndWidth() {
        Rectangle actual = new Rectangle(10, 10)
            .setHeight(3)
            .setWidth(3);
        assertThat(actual.area()).isEqualTo(9);
    }

}
