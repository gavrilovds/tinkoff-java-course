package edu.project2.generator;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project2.model.Cell.Type;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.renderer.UnicodeRenderer;
import edu.project2.solver.DepthFirstSearchSolver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrimMazeGeneratorTest {

    @Test
    @DisplayName("#generate test")
    public void generate_shouldReturnMazeWhichHaveSolution() {
        Maze testMaze = new PrimMazeGenerator().generate(13, 13);
        List<Coordinate> actual =
            new DepthFirstSearchSolver().solve(testMaze, new Coordinate(1, 1), findPassageCell(testMaze));
        assertThat(actual).isNotEmpty();
    }

    private Coordinate findPassageCell(Maze maze) {
        for (int i = maze.getHeight() - 1; i >= 0; i--) {
            for (int j = maze.getWidth() - 1; j >= 0; j--) {
                if (maze.getGrid()[i][j].getType() == Type.PASSAGE) {
                    return new Coordinate(i, j);
                }
            }
        }
        return null;
    }
}
