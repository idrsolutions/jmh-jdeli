/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.png.options.PngCompressionFormat;
import com.idrsolutions.image.png.options.PngEncoderOptions;
import data.WriteData;
import org.apache.commons.imaging.ImageFormat;
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
public class PNG {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.setTestImages();
        final String[] names = WriteData.getNames();

    }

    @Benchmark
    public void ImageIO(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByImageIO("png")) {

            int count = 0;

            for (BufferedImage img : images.testImage) {
                try {
                    ImageIOUtils.write(img, "PNG", new File(WriteData.rootDir + "png/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-imageio.png"));
                    count++;
                } catch (Exception ex) {
                     ex.printStackTrace();
                }
            }
        }
    }

    @Benchmark
    public void JDeli_FAST(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("png")) {

            int count = 0;

            try {
                PngEncoderOptions options = new PngEncoderOptions();
                options.setCompressionFormat(PngCompressionFormat.ZLIB_BETTER_SPEED);

                for (BufferedImage img : images.testImage) {
                    JDeli.write(img, options, new File(WriteData.rootDir + "png/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-jdeli-fast.png"));
                    count++;
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    public void JDeli_COMPRESS(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("png")) {

            int count = 0;

            try {
                PngEncoderOptions options = new PngEncoderOptions();
                //options.setCompressionFormat(PngCompressionFormat.ZLIB_BETTER_COMPRESSION);

                for (BufferedImage img : images.testImage) {

                    JDeli.write(img, options, new File(WriteData.rootDir + "png/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-jdeli-compress.png"));
                    count++;
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    public void JDeli_QUANT(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("png")) {

            int count = 0;

            try {
                PngEncoderOptions options = new PngEncoderOptions();
                options.setCompressionFormat(PngCompressionFormat.QUANTISED8BIT);

                for (BufferedImage img : images.testImage) {

                    JDeli.write(img, options, new File(WriteData.rootDir + "png/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-jdeli-quant.png"));
                    count++;
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }
    @Benchmark
    public void Apache(write.PNG.BenchmarkState images) {

        if (SupportedImageFormats.isWritingSupportedByApache("png")) {
            int count = 0;
            for (BufferedImage img : images.testImage) {
                try {
                    Imaging.writeImage(img, new File(WriteData.rootDir + "png/" + images.names[count].substring(0, images.names[count].indexOf('.')) + "-apache.png"), ImageFormats.PNG,null);
                    count++;
                } catch (Exception ex) {
                     ex.printStackTrace();
                }
            }
        }
    }
}
