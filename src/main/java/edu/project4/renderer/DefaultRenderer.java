package edu.project4.renderer;

import edu.project4.AffineCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultRenderer implements Renderer {

    private static final int START = -20;

    @Override
    public FractalImage render(
        FractalImage canvas, Rect world, List<Transformation> transformations,
        int samples, int iterPerSample, long seed
    ) {
        AffineCoefficients[] coefficients = getRandomAffineCoefficients(samples);
        for (int num = 0; num < samples; num++) {
            Point pw = world.getRandomPoint();
            for (int step = START; step < iterPerSample; step++) {
                int randomIndex = ThreadLocalRandom.current().nextInt(0, coefficients.length);
                AffineCoefficients coefficient = coefficients[randomIndex];
                double x = coefficient.a() * pw.x() + coefficient.b() * pw.y()
                    + coefficient.c();
                double y = coefficient.d() * pw.x() + coefficient.e() * pw.y()
                    + coefficient.f();
                pw = new Point(x, y);
                Transformation transformation =
                    transformations.get(ThreadLocalRandom.current().nextInt(0, transformations.size()));
                pw = transformation.apply(pw);
                if (step >= 0) {
                    if (!world.doesContainPoint(pw)) {
                        continue;
                    }
                    Pixel pixel =
                        canvas.getPixel(
                            (int)
                                ((pw.x() - world.x()) * canvas.getWidth() / world.width()),
                            (int) ((pw.y() - world.y()) * canvas.getHeight() / world.height())
                        );
                    if (pixel == null) {
                        continue;
                    }
                    if (pixel.getHitCount() == 0) {
                        pixel.setR(coefficient.color().getRed());
                        pixel.setG(coefficient.color().getGreen());
                        pixel.setB(coefficient.color().getBlue());
                    } else {
                        pixel.setR((pixel.getR() + coefficient.color().getRed()) / 2);
                        pixel.setG((pixel.getG() + coefficient.color().getGreen()) / 2);
                        pixel.setB((pixel.getB() + coefficient.color().getBlue()) / 2);
                    }
                    pixel.setHitCount(pixel.getHitCount() + 1);
                }
            }
        }
        return canvas;
    }

    private AffineCoefficients[] getRandomAffineCoefficients(int samples) {
        AffineCoefficients[] transformations = new AffineCoefficients[samples];
        for (int i = 0; i < samples; i++) {
            transformations[i] = AffineCoefficients.create();
        }
        return transformations;
    }
}
