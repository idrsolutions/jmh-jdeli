/*
 * Sample code to test all possible ImageIO write options
 * using jmh.
 */
package org.sample.write;

import org.sample.data.TestImageData;
import java.awt.image.BufferedImage;
import java.io.File;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.sample.utils.ImageIOUtils;

public class Write_ImageIO {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        volatile BufferedImage[] testImage = TestImageData.getImages();
        volatile String[] names = TestImageData.getNames();

    }

    static {
        new File(TestImageData.rootDir).mkdir();
        new File(TestImageData.rootDir+"jpg").mkdir();
        new File(TestImageData.rootDir+"tif").mkdir();
        new File(TestImageData.rootDir+"png").mkdir();
    }

    @Benchmark
    public void JPG(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
           //     ImageIOUtils.write(img, "JPG", new File(TestImageData.rootDir+"jpg/" + images.names[count] + "-imageio.jpg"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void PNG(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                ImageIOUtils.write(img, "PNG", new File(TestImageData.rootDir+"png/" + images.names[count] + "-imageio.png"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void TIFF(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                ImageIOUtils.write(img, "JPG", new File(TestImageData.rootDir+"tif/" + images.names[count] + "-imageio.tif"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
