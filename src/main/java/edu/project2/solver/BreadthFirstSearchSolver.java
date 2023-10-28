package edu.project2.solver;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchSolver extends AbstractSolver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (!isCoordinatesValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        initSolver(maze);
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate[][] parents = new Coordinate[maze.getHeight()][maze.getWidth()];
        queue.add(start);
        visited[start.row()][start.column()] = true;
        parents[start.row()][start.column()] = start;
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            List<Coordinate> neighbours = getNeighbours(current);
            for (Coordinate neighbour : neighbours) {
                if (!visited[neighbour.row()][neighbour.column()]) {
                    visited[neighbour.row()][neighbour.column()] = true;
                    queue.add(neighbour);
                    parents[neighbour.row()][neighbour.column()] = new Coordinate(current.row(), current.column());
                }
            }
        }
        if (!visited[end.row()][end.column()]) {
            return Collections.emptyList();
        }
        path.add(end);
        Coordinate current = parents[end.row()][end.column()];
        while (!current.equals(start)) {
            path.add(current);
            current = parents[current.row()][current.column()];
        }
        path.add(start);
        return path;
    }
}
