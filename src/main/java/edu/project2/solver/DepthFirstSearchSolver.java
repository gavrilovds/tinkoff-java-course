package edu.project2.solver;

import edu.project2.model.Coordinates;
import edu.project2.model.Maze;
import java.util.Collections;
import java.util.List;

public class DepthFirstSearchSolver extends AbstractSolver {

    @Override
    public List<Coordinates> solve(Maze maze, Coordinates start, Coordinates end) {
        if (!isCoordinatesValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        initSolver(maze);
        if (!dfs(start, end)) {
            return Collections.emptyList();
        }
        return path;
    }

    private boolean dfs(Coordinates current, Coordinates end) {
        visited[current.row()][current.column()] = true;
        path.add(current);
        if (current.equals(end)) {
            return true;
        }
        List<Coordinates> neighbours = getNeighbours(current);
        for (Coordinates neighbour : neighbours) {
            if (!visited[neighbour.row()][neighbour.column()]) {
                if (dfs(neighbour, end)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }
}
