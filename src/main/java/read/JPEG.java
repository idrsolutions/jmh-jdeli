/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package read;

import com.idrsolutions.image.JDeli;
import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.*;
import data.ReadData;
import org.openjdk.jmh.infra.Blackhole;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

/**
 *
 */
public class JPEG extends ReadTest {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final String[] filesToRead = Arrays.stream(ReadData.getReadTestFiles("jpeg")).toArray(String[]::new);

    }

    /**
     * one test must be in this class to run correctly in IDEA
     *
     * @param images the data to use for testing
     */

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void JDeli(read.JPEG.BenchmarkState images, Blackhole bh) {

        if (SupportedImageFormats.isReadingSupportedByJDeli()) {
            for (String jpgFile : images.filesToRead) {
                try {
                    BufferedImage img = JDeli.read(new File(jpgFile));
                    bh.consume(img);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void Apache(read.JPEG.BenchmarkState images, Blackhole bh) {

        if (SupportedImageFormats.isReadingSupportedByApache()) {
            for (String imageFile : images.filesToRead) {
                try {
                    BufferedImage img = Imaging.getBufferedImage(new File(imageFile));
                    bh.consume(img);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
