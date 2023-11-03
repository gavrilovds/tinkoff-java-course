package edu.project2.generator;

import edu.project2.model.Cell;
import edu.project2.model.Cell.Type;
import edu.project2.model.Maze;
import edu.project2.util.MazeUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimMazeGenerator implements MazeGenerator {

    @Override
    public Maze generate(int height, int width) {
        List<Cell> walls = new ArrayList<>();
        boolean[][] visited = new boolean[height][width];
        Cell[][] grid = MazeUtils.getOnlyWallCells(height, width);
        Maze maze = new Maze(height, width, grid);

        markAsPassage(1, 1, visited, maze, walls);
        while (!walls.isEmpty()) {
            Collections.shuffle(walls);
            Cell randomCell = walls.get(0);
            List<Cell> neighbours =
                getNeighbours(randomCell.getRow(), randomCell.getColumn(), maze);

            int visitedNeighboursCounter = 0;
            for (Cell neighbour : neighbours) {
                if (visited[neighbour.getRow()][neighbour.getColumn()]) {
                    visitedNeighboursCounter++;
                }
            }

            if (visitedNeighboursCounter == 1 && !MazeUtils.isCoordinatesOutOfMaze(
                randomCell.getRow(),
                randomCell.getColumn(),
                height,
                width
            )) {
                markAsPassage(randomCell.getRow(), randomCell.getColumn(), visited, maze, walls);
            }

            walls.remove(randomCell);
        }
        return new Maze(height, width, grid);
    }

    private List<Cell> getNeighbours(int row, int column, Maze maze) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        Cell[][] grid = maze.getGrid();
        List<Cell> neighbours = new ArrayList<>();
        if (!MazeUtils.isCoordinatesOutOfMaze(row - 1, column, height, width)) {
            neighbours.add(grid[row - 1][column]);
        }
        if (!MazeUtils.isCoordinatesOutOfMaze(row + 1, column, height, width)) {
            neighbours.add(grid[row + 1][column]);
        }
        if (!MazeUtils.isCoordinatesOutOfMaze(row, column - 1, height, width)) {
            neighbours.add(grid[row][column - 1]);
        }
        if (!MazeUtils.isCoordinatesOutOfMaze(row, column + 1, height, width)) {
            neighbours.add(grid[row][column + 1]);
        }
        return neighbours;
    }

    private void markAsPassage(int row, int column, boolean[][] visited, Maze maze, List<Cell> walls) {
        visited[row][column] = true;
        maze.getGrid()[row][column].setType(Type.PASSAGE);
        List<Cell> neighbours = getNeighbours(row, column, maze);
        if (row != maze.getHeight() - 2 && row != maze.getWidth() - 2) {
            walls.addAll(neighbours);
        }
    }
}
