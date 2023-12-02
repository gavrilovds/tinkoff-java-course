package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Rect;
import edu.project4.renderer.DefaultRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.transformation.DiscTransformation;
import edu.project4.transformation.HandkerchiefTransformation;
import edu.project4.transformation.HeartTransformation;
import edu.project4.transformation.PolarTransformation;
import edu.project4.transformation.SinusTransformation;
import edu.project4.transformation.SphereTransformation;
import edu.project4.transformation.SwirlTransformation;
import edu.project4.transformation.TangentTransformation;
import edu.project4.util.ImageUtils;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Rect rect = new Rect(-4, -3, 8, 6);
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        Renderer renderer = new DefaultRenderer();
        fractalImage = renderer.render(
            fractalImage,
            rect,
            List.of(new HeartTransformation(), new TangentTransformation()),
            5,
            100000000,
            0
        );
        ImageUtils.save(fractalImage, Path.of("image.png"), ImageFormat.PNG);
    }
}
