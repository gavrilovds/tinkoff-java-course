package edu.hw1.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessUtilsTest {

    @Test
    @DisplayName("Тестирование knightBoardCapture #1")
    public void matrix_shouldReturnTrue() {
        int[][] testBoard = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        boolean actual = ChessUtils.knightBoardCapture(testBoard);
        assertThat(actual).isEqualTo(true);

    }

    @Test
    @DisplayName("Тестирование knightBoardCapture #2")
    public void matrix_shouldReturnFalse() {
        int[][] testBoard = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        boolean actual = ChessUtils.knightBoardCapture(testBoard);
        assertThat(actual).isEqualTo(false);

    }

    @Test
    @DisplayName("Тестирование knightBoardCapture #3")
    public void matrix_shouldThrowException_whenIsEmptyOrWrongRowsNumber() {
        int[][] testBoard = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
        };

        assertThatThrownBy(() -> ChessUtils.knightBoardCapture(testBoard)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("Тестирование knightBoardCapture #4")
    public void matrix_shouldThrowException_whenIsEmptyOrWrongColumnsNumber() {
        int[][] testBoard = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        assertThatThrownBy(() -> ChessUtils.knightBoardCapture(testBoard)).isInstanceOf(IllegalArgumentException.class);

    }
}
