package edu.project2.generator;

import edu.project2.model.Cell;
import edu.project2.model.Cell.Type;
import edu.project2.model.Maze;
import edu.project2.util.MazeUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthFirstMazeGenerator implements MazeGenerator {

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        boolean[][] visited = new boolean[height][width];
        Maze maze = new Maze(height, width, grid);
        prepareGrid(maze, visited);

        Stack<Cell> cellStack = new Stack<>();
        Cell currentCell = grid[1][1];
        visited[1][1] = true;

        while (gridHasUnvisitedCells(visited)) {
            List<NeighbourWithDirection> unvisitedNeighbours = getUnvisitedNeighbours(
                currentCell.getRow(),
                currentCell.getColumn(),
                maze,
                visited
            );
            if (!unvisitedNeighbours.isEmpty()) {
                cellStack.push(currentCell);
                Collections.shuffle(unvisitedNeighbours);
                Cell randomNeighbour = unvisitedNeighbours.get(0).neighbour();
                switch (unvisitedNeighbours.get(0).direction()) {
                    case UP -> {
                        grid[randomNeighbour.getRow() + 1][randomNeighbour.getColumn()].setType(Type.PASSAGE);
                    }
                    case DOWN -> {
                        grid[randomNeighbour.getRow() - 1][randomNeighbour.getColumn()].setType(Type.PASSAGE);
                    }
                    case LEFT -> {
                        grid[randomNeighbour.getRow()][randomNeighbour.getColumn() + 1].setType(Type.PASSAGE);
                    }
                    default -> {
                        grid[randomNeighbour.getRow()][randomNeighbour.getColumn() - 1].setType(Type.PASSAGE);
                    }
                }
                currentCell = randomNeighbour;
                visited[randomNeighbour.getRow()][randomNeighbour.getColumn()] = true;
            } else {
                currentCell = cellStack.pop();
            }
        }

        return maze;
    }

    private void prepareGrid(Maze maze, boolean[][] visited) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0) && (i < height - 1 && j < width - 1)) {
                    maze.getGrid()[i][j] = new Cell(i, j, Type.PASSAGE);
                } else {
                    maze.getGrid()[i][j] = new Cell(i, j, Type.WALL);
                    visited[i][j] = true;
                }
            }
        }
    }

    private boolean gridHasUnvisitedCells(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (!visited[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<NeighbourWithDirection> getUnvisitedNeighbours(
        int row,
        int column,
        Maze maze,
        boolean[][] visited
    ) {
        List<NeighbourWithDirection> unvisitedNeighbours = new ArrayList<>();
        int height = maze.getHeight();
        int width = maze.getWidth();
        Cell[][] grid = maze.getGrid();

        if (!MazeUtils.isCoordinatesOutOfMaze(row - 2, column, height, width) && !visited[row - 2][column]) {
            unvisitedNeighbours.add(new NeighbourWithDirection(grid[row - 2][column], NeighbourDirection.UP));
        }
        if (!MazeUtils.isCoordinatesOutOfMaze(row + 2, column, height, width) && !visited[row + 2][column]) {
            unvisitedNeighbours.add(new NeighbourWithDirection(grid[row + 2][column], NeighbourDirection.DOWN));
        }
        if (!MazeUtils.isCoordinatesOutOfMaze(row, column - 2, height, width) && !visited[row][column - 2]) {
            unvisitedNeighbours.add(new NeighbourWithDirection(grid[row][column - 2], NeighbourDirection.LEFT));
        }
        if (!MazeUtils.isCoordinatesOutOfMaze(row, column + 2, height, width) && !visited[row][column + 2]) {
            unvisitedNeighbours.add(new NeighbourWithDirection(grid[row][column + 2], NeighbourDirection.RIGHT));
        }

        return unvisitedNeighbours;
    }

    private record NeighbourWithDirection(Cell neighbour, NeighbourDirection direction) {

    }

    private enum NeighbourDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT,
    }
}
