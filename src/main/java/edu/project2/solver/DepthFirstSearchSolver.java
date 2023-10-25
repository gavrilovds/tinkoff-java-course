package edu.project2.solver;

import edu.project2.model.Cell.Type;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.util.MazeUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstSearchSolver implements Solver {

    private final List<Coordinate> path = new ArrayList<>();

    private boolean[][] visited;
    private Maze maze;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (!isCoordinatesValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        this.maze = maze;
        visited = new boolean[maze.getHeight()][maze.getWidth()];
        if (!dfs(start, end)) {
            return Collections.emptyList();
        }
        return path;
    }

    private boolean dfs(Coordinate current, Coordinate end) {
        visited[current.row()][current.column()] = true;
        path.add(current);
        if (current.equals(end)) {
            return true;
        }
        List<Coordinate> neighbours = getNeighbourCoordinates(current.row(), current.column());
        for (Coordinate neighbour : neighbours) {
            if (!visited[neighbour.row()][neighbour.column()]) {
                if (dfs(neighbour, end)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    private List<Coordinate> getNeighbourCoordinates(int row, int column) {
        List<Coordinate> neighbours = new ArrayList<>();
        int height = maze.getHeight();
        int width = maze.getWidth();
        if (!MazeUtils.isOffset(row + 1, column, height, width)
            && maze.getGrid()[row + 1][column].getType() == Type.PASSAGE) {
            neighbours.add(new Coordinate(row + 1, column));
        }
        if (!MazeUtils.isOffset(row - 1, column, height, width)
            && maze.getGrid()[row - 1][column].getType() == Type.PASSAGE) {
            neighbours.add(new Coordinate(row - 1, column));
        }
        if (!MazeUtils.isOffset(row, column + 1, height, width)
            && maze.getGrid()[row][column + 1].getType() == Type.PASSAGE) {
            neighbours.add(new Coordinate(row, column + 1));
        }
        if (!MazeUtils.isOffset(row, column - 1, height, width)
            && maze.getGrid()[row][column - 1].getType() == Type.PASSAGE) {
            neighbours.add(new Coordinate(row, column - 1));
        }
        return neighbours;
    }

}
