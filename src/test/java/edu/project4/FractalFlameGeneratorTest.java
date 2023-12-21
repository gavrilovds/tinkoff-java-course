package edu.project4;

import edu.project4.image_processor.GammaCorrectionImageProcessor;
import edu.project4.model.ImageFormat;
import edu.project4.model.Rect;
import edu.project4.renderer.ParallelRenderer;
import edu.project4.transformation.PowerTransformation;
import edu.project4.transformation.SinusTransformation;
import edu.project4.transformation.SphereTransformation;
import edu.project4.transformation.SwirlTransformation;
import edu.project4.transformation.TangentTransformation;
import java.nio.file.Path;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FractalFlameGeneratorTest {

    @Test
    @DisplayName("#generate test")
    public void generate_shouldGenerateFractalFlameImage(@TempDir Path path) {
        FractalFlameGenerator generator = new FractalFlameGenerator(
            1920,
            1080,
            new ParallelRenderer(),
            new Rect(-4, -3, 8, 6),
            List.of(
                new PowerTransformation(),
                new SinusTransformation(),
                new SphereTransformation(),
                new SwirlTransformation(),
                new TangentTransformation()
            ),
            new GammaCorrectionImageProcessor()
        );
        generator.generate(1000, 1000, 2, path.resolve("img.png"), ImageFormat.PNG);
        Assertions.assertThat(path.resolve("img.png")).exists();
    }
}
