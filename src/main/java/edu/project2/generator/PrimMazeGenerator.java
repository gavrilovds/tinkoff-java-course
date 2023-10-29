package edu.project2.generator;

import edu.project2.model.Cell;
import edu.project2.model.Cell.Type;
import edu.project2.model.Maze;
import edu.project2.util.MazeUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimMazeGenerator extends AbstractGenerator {

    private final List<Cell> walls = new ArrayList<>();

    @Override
    public Maze generate(int height, int width) {
        initGenerator(height, width);
        grid = MazeUtils.getOnlyWallCells(height, width);
        markAsPassage(1, 1);
        while (!walls.isEmpty()) {
            Collections.shuffle(walls);
            Cell randomCell = walls.get(0);
            List<Cell> neighbours =
                getNeighbours(randomCell.getRow(), randomCell.getColumn());

            int visitedNeighboursCounter = 0;
            for (Cell neighbour : neighbours) {
                if (visited[neighbour.getRow()][neighbour.getColumn()]) {
                    visitedNeighboursCounter++;
                }
            }

            if (visitedNeighboursCounter == 1 && !MazeUtils.isOffset(
                randomCell.getRow(),
                randomCell.getColumn(),
                height,
                width
            )) {
                markAsPassage(randomCell.getRow(), randomCell.getColumn());
            }

            walls.remove(randomCell);
        }
        return new Maze(height, width, grid);
    }

    private List<Cell> getNeighbours(int row, int column) {
        List<Cell> neighbours = new ArrayList<>();
        if (!MazeUtils.isOffset(row - 1, column, height, width)) {
            neighbours.add(grid[row - 1][column]);
        }
        if (!MazeUtils.isOffset(row + 1, column, height, width)) {
            neighbours.add(grid[row + 1][column]);
        }
        if (!MazeUtils.isOffset(row, column - 1, height, width)) {
            neighbours.add(grid[row][column - 1]);
        }
        if (!MazeUtils.isOffset(row, column + 1, height, width)) {
            neighbours.add(grid[row][column + 1]);
        }
        return neighbours;
    }

    private void markAsPassage(int row, int column) {
        visited[row][column] = true;
        grid[row][column].setType(Type.PASSAGE);
        List<Cell> neighbours = getNeighbours(row, column);
        if (row != height - 2 && row != width - 2) {
            walls.addAll(neighbours);
        }
    }
}
