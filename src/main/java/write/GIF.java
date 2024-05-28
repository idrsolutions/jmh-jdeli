/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.gif.options.GifEncoderOptions;
import com.idrsolutions.image.png.options.PngCompressionFormat;
import com.idrsolutions.image.png.options.PngEncoderOptions;
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

        final BufferedImage[] testImage = WriteData.setTestImages();;
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
}