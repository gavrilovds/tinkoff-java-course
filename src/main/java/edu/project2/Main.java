package edu.project2;

import edu.project2.generator.DepthFirstMazeGenerator;
import edu.project2.generator.PrimMazeGenerator;
import edu.project2.model.Maze;
import edu.project2.renderer.UnicodeRenderer;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Maze maze = new DepthFirstMazeGenerator().generate(21, 21);
        System.out.print(new UnicodeRenderer().render(maze));
        //new MazeApplication().run();
    }
}
