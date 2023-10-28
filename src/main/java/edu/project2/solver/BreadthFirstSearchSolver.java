package edu.project2.solver;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public class BreadthFirstSearchSolver implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (!isCoordinatesValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        return null;
    }
}
