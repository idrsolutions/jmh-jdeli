/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.bmp.options.BmpEncoderOptions;
import data.WriteData;
import org.openjdk.jmh.annotations.*;
import utils.ImageIOUtils;

import java.awt.image.BufferedImage;
import java.io.File;

public class HEIC {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.setTestImages();
        final String[] names = WriteData.getNames();
    }

    static {
        new File(WriteData.rootDir).mkdirs();
        new File(WriteData.rootDir + "heic").mkdirs();
    }

    @Benchmark
    public void JDeli(BenchmarkState images) {

        int count = 0;

        try {
            for (BufferedImage img : images.testImage) {
                JDeli.write(img, new BmpEncoderOptions(), new File(WriteData.rootDir + "heic/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-jdeli.heic"));
                count++;
            }

        } catch (Exception ex) {
             ex.printStackTrace();
        }
    }
}
