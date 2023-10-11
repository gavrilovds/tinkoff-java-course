package edu.hw2.task2;

public class Square extends Rectangle {

    @Override
    void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }

}
