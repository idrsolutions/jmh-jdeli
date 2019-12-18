/*
 * Sample code to test all possible ImageIO write options
 * using jmh.
 *
 * This needs the PngEncoder jar from http://objectplanet.com/pngencoder/
 * to run so commented out by
 */
package org.sample.write;

import org.sample.data.TestImageData;
import com.objectplanet.image.PngEncoder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class Write_PngEncoder {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        volatile BufferedImage[] testImage = TestImageData.getImages();
        volatile String[] names = TestImageData.getNames();

    }

    static {
        new File(TestImageData.rootDir).mkdirs();
        new File(TestImageData.rootDir + "png").mkdirs();
    }


    @Benchmark
    public void PNG(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                com.objectplanet.image.PngEncoder encoder = new com.objectplanet.image.PngEncoder();
                encoder.encode(img, new FileOutputStream(TestImageData.rootDir + "png/" + images.names[count] + "-pngencoder.png"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    
    @Benchmark
    public void PNG_GREY_FAST(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                com.objectplanet.image.PngEncoder encoder = new com.objectplanet.image.PngEncoder(PngEncoder.COLOR_GRAYSCALE_ALPHA, PngEncoder.BEST_SPEED);
                encoder.encode(img, new FileOutputStream(TestImageData.rootDir + "png/" + images.names[count] + "-pngencoder-grey-fast-alpha.png"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    
    @Benchmark
    public void PNG_GREY_BEST(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                com.objectplanet.image.PngEncoder encoder = new com.objectplanet.image.PngEncoder(PngEncoder.COLOR_GRAYSCALE_ALPHA, PngEncoder.BEST_COMPRESSION);
                encoder.encode(img, new FileOutputStream(TestImageData.rootDir + "png/" + images.names[count] + "-pngencoder-grey-fast-alpha.png"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    
    @Benchmark
    public void PNG_INDEXED_FAST(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                com.objectplanet.image.PngEncoder encoder = new com.objectplanet.image.PngEncoder(PngEncoder.COLOR_INDEXED_ALPHA, PngEncoder.BEST_SPEED);
                encoder.encode(img, new FileOutputStream(TestImageData.rootDir + "png/" + images.names[count] + "-pngencoder-indexed-fast-alpha.png"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    
    @Benchmark
    public void PNG_INDEXED_BEST(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                com.objectplanet.image.PngEncoder encoder = new com.objectplanet.image.PngEncoder(PngEncoder.COLOR_INDEXED_ALPHA, PngEncoder.BEST_COMPRESSION);
                encoder.encode(img, new FileOutputStream(TestImageData.rootDir + "png/" + images.names[count] + "-pngencoder-indexed-fast-alpha.png"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void PNG_TRUECOLOR_FAST(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                com.objectplanet.image.PngEncoder encoder = new com.objectplanet.image.PngEncoder(PngEncoder.COLOR_TRUECOLOR_ALPHA, PngEncoder.BEST_SPEED);
                encoder.encode(img, new FileOutputStream(TestImageData.rootDir + "png/" + images.names[count] + "-pngencoder-truecolor-fast-alpha.png"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    
    @Benchmark
    public void PNG_TRUECOLOR_BEST(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                com.objectplanet.image.PngEncoder encoder = new com.objectplanet.image.PngEncoder(PngEncoder.COLOR_TRUECOLOR_ALPHA, PngEncoder.BEST_COMPRESSION);
                encoder.encode(img, new FileOutputStream(TestImageData.rootDir + "png/" + images.names[count] + "-pngencoder-truecolor-fast-alpha.png"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
