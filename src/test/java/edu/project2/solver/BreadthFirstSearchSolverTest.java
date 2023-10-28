package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.solver.BreadthFirstSearchSolver;
import edu.project2.solver.DepthFirstSearchSolver;
import edu.project2.solver.Solver;
import edu.project2.util.MazeUtils;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class BreadthFirstSearchSolverTest {

    private final Solver solver = new BreadthFirstSearchSolver();
    private static Maze maze;

    @BeforeAll
    static void initMaze() {
        Cell[][] grid = MazeUtils.getOnlyPassageCells(5, 5);
        MazeUtils.createOffset(grid);
        maze = new Maze(5, 5, grid);
    }

    @Test
    @DisplayName("BFS solver test")
    public void solve_shouldFindCorrectPath() {
        List<Coordinate> actual = solver.solve(maze, new Coordinate(1, 1), new Coordinate(3, 3));
        Collections.reverse(actual);
        assertThat(actual).containsExactly(new Coordinate(1, 1), new Coordinate(2, 1),
            new Coordinate(3, 1), new Coordinate(3, 2), new Coordinate(3, 3)
        );
    }

    @Test
    @DisplayName("BFS solver test with wrong input")
    public void solve_shouldThrowExceptionWhenInputIsIncorrect() {
        assertThatThrownBy(() -> solver.solve(maze, new Coordinate(-1, 0), new Coordinate(9, 9))).isInstanceOf(
            IllegalArgumentException.class);
    }
}
