/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.gif.options.GifEncoderOptions;
import data.WriteData;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.*;
import utils.ImageIOUtils;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 */
public class GIF {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.setTestImages();
        final String[] names = WriteData.getNames();

    }

    @Benchmark
    public void ImageIO(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByImageIO("gif")) {

            int count = 0;

            for (BufferedImage img : images.testImage) {
                try {
                    ImageIOUtils.write(img, "GIF", new File(WriteData.rootDir + "gif/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-imageio.gif"));
                    count++;
                } catch (Exception ex) {
                     ex.printStackTrace();
                }
            }
        }
    }

    @Benchmark
    public void JDeli(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("gif")) {

            int count = 0;

            try {

                for (BufferedImage img : images.testImage) {
                    JDeli.write(img, new GifEncoderOptions(), new File(WriteData.rootDir + "gif/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-jdeli.gif"));
                    count++;
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void Apache(BMP.BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByApache("gif")) {
            int count = 0;

            try {
                for (BufferedImage img : images.testImage) {
                    Imaging.writeImage(img, new File(WriteData.rootDir + "gif/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-apache.gif"), ImageFormats.GIF);
                    count++;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}