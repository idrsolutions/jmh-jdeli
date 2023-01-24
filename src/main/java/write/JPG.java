/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.jpeg.options.JpegEncoderOptions;
import data.WriteData;
import org.openjdk.jmh.annotations.*;
import utils.ImageIOUtils;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;

public class JPG {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.setTestImages();
        final String[] names = WriteData.getNames();

    }

    static {
        new File(WriteData.rootDir).mkdirs();
        new File(WriteData.rootDir + "jpg").mkdirs();
    }

    @Benchmark
    public void JDeli(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("jpg")) {

            int count = 0;

            try {
                JpegEncoderOptions options = new JpegEncoderOptions();

                for (BufferedImage img : images.testImage) {
                    JDeli.write(img, options, new File(WriteData.rootDir + "jpg/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-jdeli.jpg"));
                    count++;
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    public void ImageIO(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByImageIO("jpg")) {

            int count = 0;

            for (BufferedImage img : images.testImage) {
                try {
                    ImageIOUtils.write(img, "JPG", new File(WriteData.rootDir + "jpg/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-imageio.jpg"));
                    count++;
                } catch (Exception ex) {
                     ex.printStackTrace();
                }
            }
        }
    }
}
