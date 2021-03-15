/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package read;

import java.awt.image.BufferedImage;
import java.io.File;

import com.idrsolutions.image.JDeli;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import data.ReadData;
import utils.SupportedImageFormats;

/**
 *
 */
public class PNG extends ReadTest {

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
    public void JDeli(PNG.BenchmarkState images) {

        if (SupportedImageFormats.isSupportedByJDeli()) {
            for (String pngFile : images.filesToRead) {
                try {
                    BufferedImage img = JDeli.read(new File(pngFile));
                } catch (Exception ex) {
                    //   System.out.println(ex);
                }
            }
        }
    }
}
