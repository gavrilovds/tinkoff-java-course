package edu.project4.image_processor;

import edu.project4.model.FractalImage;

public class GammaCorrectionImageProcessor implements ImageProcessor {

    @Override
    public void process(FractalImage image) {
        double max = 0;
        double gamma = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getPixel(x, y).getHitCount() != 0) {
                    //
                }
            }
        }
    }
}
