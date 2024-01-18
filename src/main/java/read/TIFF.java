package read;

import com.idrsolutions.image.JDeli;
import data.ReadData;
import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
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
    @BenchmarkMode(Mode.Throughput)
    public void JDeli(BenchmarkState images, Blackhole bh) {

        if (SupportedImageFormats.isReadingSupportedByJDeli()) {
            for (String tiffFile : images.filesToRead) {
                try {

                    BufferedImage img = JDeli.read(new File(tiffFile));
                    bh.consume(img);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void Apache(read.TIFF.BenchmarkState images, Blackhole bh) {

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
