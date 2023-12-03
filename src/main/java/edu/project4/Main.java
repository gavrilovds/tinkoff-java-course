package edu.project4;

import edu.project4.image_processor.GammaCorrectionImageProcessor;
import edu.project4.image_processor.ImageProcessor;
import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Rect;
import edu.project4.renderer.ParallelRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.transformation.CylinderTransformation;
import edu.project4.transformation.HandkerchiefTransformation;
import edu.project4.transformation.SwirlTransformation;
import edu.project4.util.ImageUtils;
import java.nio.file.Path;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass

public class Main {

    public static void main(String[] args) {
        Rect rect = new Rect(-4, -3, 8, 6);
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        Renderer renderer = new ParallelRenderer();
        fractalImage = renderer.render(
            fractalImage,
            rect,
            List.of(new CylinderTransformation(), new SwirlTransformation()),
            10,
            100_000_00,
            11
        );
        ImageProcessor imageProcessor = new GammaCorrectionImageProcessor();
        imageProcessor.process(fractalImage);
        ImageUtils.save(fractalImage, Path.of("image.png"), ImageFormat.PNG);
    }
}
