/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.bmp.options.BmpEncoderOptions;
import data.WriteData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.awt.image.BufferedImage;
import java.io.File;

public class BMP {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.getImages();
        final String[] names = WriteData.getNames();

    }

    static {
        new File(WriteData.rootDir).mkdirs();
        new File(WriteData.rootDir + "bmp").mkdirs();
    }

    @Benchmark
    public void JDeli(BenchmarkState images) {

        int count = 0;

        try {
            BmpEncoderOptions options = new BmpEncoderOptions();

            for (BufferedImage img : images.testImage) {
                JDeli.write(img, options, new File(WriteData.rootDir + "bmp/" + images.names[count] + "-jdeli.bmp"));
                count++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
