/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.bmp.options.BmpEncoderOptions;
import data.WriteData;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.*;
import utils.ImageIOUtils;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;

public class BMP {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.setTestImages();
        final String[] names = WriteData.getNames();
    }

    static {
        new File(WriteData.rootDir).mkdirs();
        new File(WriteData.rootDir + "bmp").mkdirs();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void JDeli(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("bmp")) {
            int count = 0;

            try {
                for (BufferedImage img : images.testImage) {
                    JDeli.write(img, new BmpEncoderOptions(), new File(WriteData.rootDir + "bmp/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-jdeli.bmp"));
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
        if (SupportedImageFormats.isWritingSupportedByImageIO("bmp")) {
            int count = 0;
            try {
                for (BufferedImage img : images.testImage) {

                    ImageIOUtils.write(img, "BMP", new File(WriteData.rootDir + "bmp/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-imageio.bmp"));
                    count++;
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void Apache(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByApache("bmp")) {
        int count = 0;

        try {
            for (BufferedImage img : images.testImage) {
                Imaging.writeImage(img, new File(WriteData.rootDir + "bmp/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-apache.bmp"), ImageFormats.BMP);
                count++;
            }

        } catch (Exception ex) {
             ex.printStackTrace();
        }
    }
    }
}
