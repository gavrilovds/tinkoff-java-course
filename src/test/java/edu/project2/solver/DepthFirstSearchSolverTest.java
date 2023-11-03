package edu.project2.solver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import edu.project2.model.Cell;
import edu.project2.model.Cell.Type;
import edu.project2.model.Coordinates;
import edu.project2.model.Maze;
import edu.project2.util.MazeUtils;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DepthFirstSearchSolverTest {

    private final Solver solver = new DepthFirstSearchSolver();
    private static Maze maze;

    @BeforeAll
    static void initMaze() {
        Cell[][] grid = MazeUtils.getOnlyPassageCells(7, 7);
        MazeUtils.createOffset(grid);
        for (int i = 2; i < 7; i += 2) {
            for (int j = 2; j < 6; j++) {
                grid[j][i].setType(Type.WALL);
            }
        }
        maze = new Maze(7, 7, grid);
    }

    @Test
    @DisplayName("DFS solver test")
    public void solve_shouldFindCorrectPath() {
        List<Coordinates> actual = solver.solve(maze, new Coordinates(1, 1), new Coordinates(5, 5));
        assertThat(actual).containsExactly(
            new Coordinates(1, 1),
            new Coordinates(1, 2),
            new Coordinates(1, 3),
            new Coordinates(1, 4),
            new Coordinates(1, 5),
            new Coordinates(2, 5),
            new Coordinates(3, 5),
            new Coordinates(4, 5),
            new Coordinates(5, 5)
        );
    }

    @Test
    @DisplayName("DFS solver test with wrong input")
    public void solve_shouldThrowExceptionWhenInputIsIncorrect() {
        assertThatThrownBy(() -> solver.solve(maze, new Coordinates(-1, 0), new Coordinates(9, 9))).isInstanceOf(
            IllegalArgumentException.class);
    }
}
