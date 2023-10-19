package edu.hw2.task2;

public class Rectangle {

    private int width;
    private int height;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Rectangle() {

    }

    public final Rectangle setHeight(int height) {
        return new Rectangle(height, width);
    }

    public final Rectangle setWidth(int width) {
        return new Rectangle(height, width);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    double area() {
        return width * height;
    }
}
