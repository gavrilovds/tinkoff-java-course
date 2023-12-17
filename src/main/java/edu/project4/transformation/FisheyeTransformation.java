package edu.project4.transformation;

import edu.project4.model.Point;

public class FisheyeTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double oldX = point.x();
        double oldY = point.y();
        double r = 2 / (1 + Math.sqrt(oldX * oldX + oldY * oldY));
        return new Point(r * oldY, r * oldX);
    }
}
