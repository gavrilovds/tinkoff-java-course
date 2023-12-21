package edu.project4.transformation;

import edu.project4.model.Point;

public class HandkerchiefTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double oldX = point.x();
        double oldY = point.y();
        double r = Math.sqrt(oldX * oldX + oldY * oldY);
        double fi = Math.atan(oldX / oldY);
        return new Point(r * Math.sin(fi + r), r * Math.cos(fi - r));
    }
}
