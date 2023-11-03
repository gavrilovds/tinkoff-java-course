package edu.project2.util;

import edu.project2.model.Cell;
import edu.project2.model.Cell.Type;

public final class MazeUtils {

    private MazeUtils() {
    }

    public static Cell[][] getOnlyWallCells(int height, int width) {
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j, Type.WALL);
            }
        }
        return cells;
    }

    public static Cell[][] getOnlyPassageCells(int height, int width) {
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j, Type.PASSAGE);
            }
        }
        return cells;
    }

    public static void createOffset(Cell[][] grid) {
        for (int i = 0; i < grid[0].length; i++) {
            grid[0][i] = new Cell(0, i, Type.WALL);
            grid[grid.length - 1][i] = new Cell(grid.length - 1, i, Type.WALL);
        }
        for (int i = 0; i < grid.length; i++) {
            grid[i][0] = new Cell(i, 0, Type.WALL);
            grid[i][grid[0].length - 1] = new Cell(i, grid[0].length - 1, Type.WALL);
        }
    }

    public static boolean isCoordinatesOutOfMaze(int row, int column, int height, int width) {
        return row <= 0 || row >= height - 1 || column <= 0 || column >= width - 1;
    }
}
