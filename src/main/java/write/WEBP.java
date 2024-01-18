/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.webp.options.WebpEncoderOptions;
import data.WriteData;

import org.openjdk.jmh.annotations.*;
import utils.ImageIOUtils;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;

public class WEBP {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.setTestImages();
        final String[] names = WriteData.getNames();

    }

    static {
        new File(WriteData.rootDir).mkdirs();
        new File(WriteData.rootDir + "webp").mkdirs();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void JDeli(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("webp")) {
            int count = 0;

            try {
                for (BufferedImage img : images.testImage) {
                    JDeli.write(img, new WebpEncoderOptions(), new File(WriteData.rootDir + "webp/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-jdeli.webp"));
                    count++;
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void ImageIO(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByImageIO("webp")) {
            int count = 0;
            try {
                for (BufferedImage img : images.testImage) {

                    ImageIOUtils.write(img, "WEBP", new File(WriteData.rootDir + "webp/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-imageio.webp"));
                    count++;
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }
}
