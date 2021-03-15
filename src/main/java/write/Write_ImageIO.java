/*
 * Sample code to test all possible ImageIO write options
 * using jmh.
 */
package write;

import data.WriteData;

import java.awt.image.BufferedImage;
import java.io.File;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import utils.ImageIOUtils;

public class Write_ImageIO {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        volatile BufferedImage[] testImage = WriteData.getImages();
        volatile String[] names = WriteData.getNames();

    }

    static {
        new File(WriteData.rootDir).mkdir();
        new File(WriteData.rootDir + "jpg").mkdir();
        new File(WriteData.rootDir + "tif").mkdir();
        new File(WriteData.rootDir + "png").mkdir();
    }

    @Benchmark
    public void JPG(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                //     ImageIOUtils.write(img, "JPG", new File(WriteData.rootDir+"jpg/" + images.names[count] + "-imageio.jpg"));
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
                ImageIOUtils.write(img, "PNG", new File(WriteData.rootDir + "png/" + images.names[count] + "-imageio.png"));
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
                ImageIOUtils.write(img, "JPG", new File(WriteData.rootDir + "tif/" + images.names[count] + "-imageio.tif"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
