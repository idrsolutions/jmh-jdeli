/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package read;

import java.awt.image.BufferedImage;
import java.io.File;

import com.idrsolutions.image.JDeli;
import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.*;
import data.ReadData;
import org.openjdk.jmh.infra.Blackhole;
import utils.SupportedImageFormats;

/**
 *
 */
public class PNG {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final static String[] filesToRead = ReadData.getReadTestFiles("png");

    }

    /**
     * one test must be in this class to run correctly in IDEa
     *
     * @param images the data to use for testing
     */

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void JDeli(BenchmarkState images, Blackhole bh) {

        if (SupportedImageFormats.isReadingSupportedByJDeli()) {
            for (String pngFile : images.filesToRead) {
                try {
                    BufferedImage img = JDeli.read(new File(pngFile));
                    bh.consume(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void Apache(read.PNG.BenchmarkState images, Blackhole bh) {

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
