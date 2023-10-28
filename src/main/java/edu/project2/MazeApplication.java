package edu.project2;

import edu.project2.generator.DepthFirstMazeGenerator;
import edu.project2.generator.MazeGenerator;
import edu.project2.generator.PrimMazeGenerator;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.printer.Printer;
import edu.project2.reader.Reader;
import edu.project2.renderer.Renderer;
import edu.project2.renderer.UnicodeRenderer;
import edu.project2.solver.BreadthFirstSearchSolver;
import edu.project2.solver.DepthFirstSearchSolver;
import edu.project2.solver.Solver;
import edu.project2.util.MessagesUtils;
import java.util.List;

public class MazeApplication {

    private final Printer printer;
    private final Reader reader;
    private MazeGenerator generator;
    private Solver solver;
    private int height;
    private int width;

    public MazeApplication(Printer printer, Reader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public void run() {
        printer.print(MessagesUtils.HELLO_MESSAGE);
        inputMazeSize();
        chooseGenerator();
        chooseSolver();
        Maze maze = generator.generate(height, width);
        Renderer renderer = new UnicodeRenderer();
        printer.print(renderer.render(maze));
        Coordinate[] coordinates = inputCoordinates();
        List<Coordinate> path = solver.solve(maze, coordinates[0], coordinates[1]);
        printer.print(renderer.renderPath(maze, path));
        printer.print(MessagesUtils.END_MESSAGE);
    }

    private void inputMazeSize() {
        printer.print(MessagesUtils.INPUT_MAZE_SIZE_MESSAGE);
        height = reader.readInt();
        width = reader.readInt();
        if (!validateSize(height, width)) {
            throw new IllegalArgumentException("Wrong size input");
        }
    }

    private void chooseGenerator() {
        printer.print(MessagesUtils.CHOOSE_GENERATOR_MESSAGE);
        int number = reader.readInt();
        generator = switch (number) {
            case 1 -> new DepthFirstMazeGenerator();
            case 2 -> new PrimMazeGenerator();
            default -> throw new IllegalArgumentException("Wrong generator input");
        };
    }

    private void chooseSolver() {
        printer.print(MessagesUtils.CHOOSE_SOLVER_MESSAGE);
        int number = reader.readInt();
        solver = switch (number) {
            case 1 -> new BreadthFirstSearchSolver();
            case 2 -> new DepthFirstSearchSolver();
            default -> throw new IllegalArgumentException("Wrong solver input");
        };
    }

    private Coordinate[] inputCoordinates() {
        printer.print(MessagesUtils.INPUT_COORDINATES_MESSAGE);
        int rowStart = reader.readInt();
        int columnStart = reader.readInt();
        int rowEnd = reader.readInt();
        int columnEnd = reader.readInt();
        return new Coordinate[] {
            new Coordinate(rowStart, columnStart), new Coordinate(rowEnd, columnEnd)
        };

    }

    private boolean validateSize(int height, int width) {
        final int maxSize = 39;
        final int minSize = 11;
        return height <= maxSize && width <= maxSize && width >= minSize && height >= minSize
            && height % 2 != 0
            && width % 2 != 0;
    }
}
