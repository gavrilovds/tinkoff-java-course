package edu.project2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Cell {

    private final int row;
    private final int column;
    @Setter
    private Type type;

    public enum Type {
        PASSAGE,
        WALL
    }
}
