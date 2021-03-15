/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.png.options.PngCompressionFormat;
import com.idrsolutions.image.png.options.PngEncoderOptions;
import data.WriteData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import utils.ImageIOUtils;

import java.awt.image.BufferedImage;
import java.io.File;

public class TIFF {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.getImages();
        final String[] names = WriteData.getNames();

    }

    static {
        new File(WriteData.rootDir + "tif").mkdirs();
    }

    @Benchmark
    public void JDeli(BenchmarkState images) {

        int count = 0;

        try {
            PngEncoderOptions options = new PngEncoderOptions();
            options.setCompressionFormat(PngCompressionFormat.QUANTISED8BIT);

            for (BufferedImage img : images.testImage) {

                JDeli.write(img, options, new File(WriteData.rootDir + "tif/" + images.names[count] + "-jdeli.tif"));
                count++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Benchmark
    public void ImageIO(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                ImageIOUtils.write(img, "TIF", new File(WriteData.rootDir + "tif/" + images.names[count] + "-imageio.tif"));
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
