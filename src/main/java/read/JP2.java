package read;

import com.idrsolutions.image.JDeli;
import data.ReadData;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;


public class JP2 extends ReadTest {


    @State(Scope.Benchmark)
    public static class BenchmarkState {

        String[] filesToRead = ReadData.getReadTestFiles("jp2");

    }

    /**
     * one test must be in this class to run correctly in IDEA
     *
     * @param images the data to use for testing
     */

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void JDeli(BenchmarkState images, Blackhole bh) {

        if (SupportedImageFormats.isSupportedByJDeli()) {
            for (String imageFile : images.filesToRead) {
                try {
                    BufferedImage img = JDeli.read(new File(imageFile));
                    bh.consume(img);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
