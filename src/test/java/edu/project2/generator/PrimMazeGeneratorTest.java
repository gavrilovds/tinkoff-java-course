package edu.project2.generator;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project2.model.Cell.Type;
import edu.project2.model.Coordinates;
import edu.project2.model.Maze;
import edu.project2.solver.DepthFirstSearchSolver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrimMazeGeneratorTest {

    @Test
    @DisplayName("#generate test")
    public void generate_shouldReturnMazeWhichHaveSolution() {
        Maze testMaze = new PrimMazeGenerator().generate(13, 13);
        List<Coordinates> actual =
            new DepthFirstSearchSolver().solve(testMaze, new Coordinates(1, 1), findPassageCell(testMaze));
        assertThat(actual).isNotEmpty();
    }

    private Coordinates findPassageCell(Maze maze) {
        for (int i = maze.getHeight() - 1; i >= 0; i--) {
            for (int j = maze.getWidth() - 1; j >= 0; j--) {
                if (maze.getGrid()[i][j].getType() == Type.PASSAGE) {
                    return new Coordinates(i, j);
                }
            }
        }
        return null;
    }
}
