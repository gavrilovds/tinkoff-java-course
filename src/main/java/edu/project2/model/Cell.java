package edu.project2.model;

public class Cell {

    private final int row;
    private final int column;
    private Type type;

    public Cell(int row, int column, Type type) {
        this.row = row;
        this.column = column;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        PASSAGE('⬛'),
        WALL('⬜');
        private final char symbol;

        Type(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
    }

}
