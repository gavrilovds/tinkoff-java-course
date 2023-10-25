package edu.project2.solver;

import edu.project2.model.Cell.Type;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.util.MazeUtils;
import java.util.List;

public interface Solver {

    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

    default boolean isCoordinatesValid(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        return !MazeUtils.isOffset(start.row(), start.column(), height, width)
            && !MazeUtils.isOffset(end.row(), end.column(), height, width)
            && maze.getGrid()[start.row()][start.column()].getType() != Type.WALL
            && maze.getGrid()[end.row()][end.column()].getType() != Type.WALL;
    }
}
