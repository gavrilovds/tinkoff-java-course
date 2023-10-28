package edu.project2.solver;

import edu.project2.model.Cell.Type;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.util.MazeUtils;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSolver implements Solver {

    private static final int[][] NEIGHBOURS_COORDINATES = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    protected final List<Coordinate> path = new ArrayList<>();
    protected boolean[][] visited;
    protected Maze maze;

    protected void initSolver(Maze maze) {
        this.maze = maze;
        visited = new boolean[maze.getHeight()][maze.getWidth()];
    }

    protected List<Coordinate> getNeighbours(Coordinate current) {
        List<Coordinate> neighbours = new ArrayList<>();
        for (int[] neighbour : NEIGHBOURS_COORDINATES) {
            int newRow = current.row() + neighbour[0];
            int newColumn = current.column() + neighbour[1];
            if (!MazeUtils.isOffset(newRow, newColumn, maze.getHeight(), maze.getWidth())
                && maze.getGrid()[newRow][newColumn].getType() == Type.PASSAGE) {
                neighbours.add(new Coordinate(newRow, newColumn));
            }
        }
        return neighbours;
    }

    protected boolean isCoordinatesValid(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        return !MazeUtils.isOffset(start.row(), start.column(), height, width)
            && !MazeUtils.isOffset(end.row(), end.column(), height, width)
            && maze.getGrid()[start.row()][start.column()].getType() != Type.WALL
            && maze.getGrid()[end.row()][end.column()].getType() != Type.WALL;
    }
}
