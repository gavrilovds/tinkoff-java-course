package edu.project4;

import edu.project4.image_processor.ImageProcessor;
import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Rect;
import edu.project4.renderer.Renderer;
import edu.project4.transformation.Transformation;
import edu.project4.util.ImageUtils;
import java.nio.file.Path;
import java.util.List;

public final class FractalFlameGenerator {

    private final FractalImage fractalImage;
    private final Renderer renderer;
    private final Rect world;
    private final List<Transformation> transformations;
    private final ImageProcessor processor;

    public FractalFlameGenerator(
        int width,
        int height,
        Renderer renderer,
        Rect world,
        List<Transformation> transformations,
        ImageProcessor processor
    ) {
        this.fractalImage = FractalImage.create(width, height);
        this.renderer = renderer;
        this.world = world;
        this.transformations = transformations;
        this.processor = processor;
    }

    public void generate(int samples, int iterPerSample, int symmetry, Path path, ImageFormat format) {
        renderer.render(fractalImage, world, transformations, samples, iterPerSample, symmetry);
        processor.process(fractalImage);
        ImageUtils.save(fractalImage, path, format);
    }
}
