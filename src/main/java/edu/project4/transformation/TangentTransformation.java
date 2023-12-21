package edu.project4.transformation;

import edu.project4.model.Point;

public class TangentTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double oldX = point.x();
        double oldY = point.y();
        return new Point(Math.sin(oldX) / Math.cos(oldY), Math.tan(oldY));
    }
}
