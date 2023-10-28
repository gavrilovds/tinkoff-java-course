package edu.project2.generator;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.solver.DepthFirstSearchSolver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class DepthFirstMazeGeneratorTest {

    @Test
    @DisplayName("#generate test")
    public void generate_shouldReturnMazeWhichHaveSolution() {
        Maze testMaze = new DepthFirstMazeGenerator().generate(13, 13);
        List<Coordinate> actual =
            new DepthFirstSearchSolver().solve(testMaze, new Coordinate(1, 1), new Coordinate(11, 11));
        assertThat(actual).isNotEmpty();
    }
}
