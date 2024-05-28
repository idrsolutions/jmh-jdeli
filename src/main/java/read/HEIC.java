/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package read;

import com.idrsolutions.image.JDeli;
import data.ReadData;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 */
public class HEIC extends ReadTest {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final static String[] filesToRead = ReadData.getReadTestFiles("heic");

    }

    /**
     * one test must be in this class to run correctly in IDEa
     *
     * @param images the data to use for testing
     */

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void JDeli(HEIC.BenchmarkState images, Blackhole bh) {

        if (SupportedImageFormats.isReadingSupportedByJDeli()) {
            for (String heicFile : images.filesToRead) {
                try {
                    BufferedImage img = JDeli.read(new File(heicFile));
                    bh.consume(img);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
