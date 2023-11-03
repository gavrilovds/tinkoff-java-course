package edu.project2.renderer;

import edu.project2.model.Cell;
import edu.project2.model.Coordinates;
import edu.project2.model.Maze;
import edu.project2.util.MazeUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class RendererTest {

    private final Renderer renderer = new UnicodeRenderer();

    @Test
    @DisplayName("#render test")
    public void render_shouldRenderGivenMaze() {
        Cell[][] grid = MazeUtils.getOnlyPassageCells(5, 5);
        MazeUtils.createOffset(grid);
        Maze testMaze = new Maze(5, 5, grid);
        assertThat(renderer.render(testMaze)).isEqualTo("""
            ⬜⬜⬜⬜⬜
            ⬜⬛⬛⬛⬜
            ⬜⬛⬛⬛⬜
            ⬜⬛⬛⬛⬜
            ⬜⬜⬜⬜⬜
            """);
    }

    @Test
    @DisplayName("#renderPath test")
    public void renderPath_shouldRenderGivenMazeWithPath() {
        Cell[][] grid = MazeUtils.getOnlyPassageCells(5, 5);
        MazeUtils.createOffset(grid);
        Maze testMaze = new Maze(5, 5, grid);
        List<Coordinates> testPath = new ArrayList<>() {{
            add(new Coordinates(1, 1));
            add(new Coordinates(1, 2));
            add(new Coordinates(2, 2));
        }};

        assertThat(renderer.renderPath(testMaze, testPath)).isEqualTo("""
            ⬜⬜⬜⬜⬜
            ⬜🟢🟢⬛⬜
            ⬜⬛🟢⬛⬜
            ⬜⬛⬛⬛⬜
            ⬜⬜⬜⬜⬜
            """);
    }
}
