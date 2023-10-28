package edu.project2;

import edu.project2.generator.DepthFirstMazeGenerator;
import edu.project2.generator.PrimMazeGenerator;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.renderer.UnicodeRenderer;
import edu.project2.solver.DepthFirstSearchSolver;
import java.util.List;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Maze maze = new DepthFirstMazeGenerator().generate(39, 39);
        System.out.println(new UnicodeRenderer().render(maze));
        List<Coordinate> list = new DepthFirstSearchSolver().solve(maze, new Coordinate(1, 1), new Coordinate(37, 37));
        System.out.println(new UnicodeRenderer().renderPath(maze, list));
    }
}
