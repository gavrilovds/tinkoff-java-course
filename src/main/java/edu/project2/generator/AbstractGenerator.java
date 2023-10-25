package edu.project2.generator;

import edu.project2.model.Cell;

public abstract class AbstractGenerator implements MazeGenerator {

    protected Cell[][] grid;
    protected boolean[][] visited;
    protected int height;
    protected int width;

    protected abstract void initGenerator(int height, int width);
}
