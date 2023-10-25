package edu.project2.renderer;

import edu.project2.model.Cell;
import edu.project2.model.Cell.Type;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public class UnicodeRenderer implements Renderer {

    private static final char PASSAGE_SYMBOL = '⬛';
    private static final char WALL_SYMBOL = '⬜';

    @Override
    public String render(Maze maze) {
        StringBuilder renderedMaze = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell currentCell = maze.getGrid()[i][j];
                if (currentCell.getType() == Type.PASSAGE) {
                    renderedMaze.append(PASSAGE_SYMBOL);
                } else {
                    renderedMaze.append(WALL_SYMBOL);
                }
            }
            renderedMaze.append('\n');
        }
        return renderedMaze.toString();
    }

    @Override
    public String renderPath(Maze maze, List<Coordinate> path) {
        return null;
    }
}
