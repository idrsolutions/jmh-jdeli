package read;

import com.idrsolutions.image.JDeli;
import data.ReadData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;

public class TIFF extends ReadTest{

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final static String[] filesToRead = ReadData.getReadTestFiles("tiff");

    }

    /**
     * one test must be in this class to run correctly in IDEa
     *
     * @param images the data to use for testing
     */

    @Benchmark
    public void JDeli(PNG.BenchmarkState images) {

        if (SupportedImageFormats.isSupportedByJDeli()) {
            for (String tiffFile : images.filesToRead) {
                try {

                    BufferedImage img = JDeli.read(new File(tiffFile));
                } catch (Exception ex) {
                    //   System.out.println(ex);
                }
            }
        }
    }
}
