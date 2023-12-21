package edu.project4.transformation;

import edu.project4.model.Point;
import java.util.concurrent.ThreadLocalRandom;

public class JuliaTransformation implements Transformation {

    private static final double JULIA_TRANSFORMATION_CONST = 0.5;

    @Override
    public Point apply(Point point) {
        double oldX = point.x();
        double oldY = point.y();
        double r = Math.sqrt(Math.sqrt(oldX * oldX + oldY * oldY));
        double theta = Math.atan(oldY / oldX) * JULIA_TRANSFORMATION_CONST;
        if (ThreadLocalRandom.current().nextBoolean()) {
            theta += Math.PI;
        }
        return new Point(r * Math.cos(theta), r * Math.sin(theta));
    }
}
