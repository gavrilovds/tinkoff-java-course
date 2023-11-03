package edu.project2.solver;

import edu.project2.model.Coordinates;
import edu.project2.model.Maze;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchSolver extends AbstractSolver {

    @Override
    public List<Coordinates> solve(Maze maze, Coordinates start, Coordinates end) {
        if (!isCoordinatesValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        initSolver(maze);
        Queue<Coordinates> queue = new LinkedList<>();
        Coordinates[][] parents = new Coordinates[maze.getHeight()][maze.getWidth()];
        queue.add(start);
        visited[start.row()][start.column()] = true;
        parents[start.row()][start.column()] = start;
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            List<Coordinates> neighbours = getNeighbours(current);
            for (Coordinates neighbour : neighbours) {
                if (!visited[neighbour.row()][neighbour.column()]) {
                    visited[neighbour.row()][neighbour.column()] = true;
                    queue.add(neighbour);
                    parents[neighbour.row()][neighbour.column()] = new Coordinates(current.row(), current.column());
                }
            }
        }
        if (!visited[end.row()][end.column()]) {
            return Collections.emptyList();
        }
        path.add(end);
        Coordinates current = parents[end.row()][end.column()];
        while (!current.equals(start)) {
            path.add(current);
            current = parents[current.row()][current.column()];
        }
        path.add(start);
        return path;
    }
}
