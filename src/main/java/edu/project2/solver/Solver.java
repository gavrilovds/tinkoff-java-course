package edu.project2.solver;

import edu.project2.model.Coordinates;
import edu.project2.model.Maze;
import java.util.List;

public interface Solver {

    List<Coordinates> solve(Maze maze, Coordinates start, Coordinates end);
}
