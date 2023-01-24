/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.tiff.options.TiffCompressionFormat;
import com.idrsolutions.image.tiff.options.TiffEncoderOptions;
import data.WriteData;
import org.openjdk.jmh.annotations.*;
import utils.ImageIOUtils;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;

public class TIFF {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.setTestImages();
        final String[] names = WriteData.getNames();

    }

    static {
        new File(WriteData.rootDir + "tif").mkdirs();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void JDeli_better_comp(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("tiff")) {


            int count = 0;

            try {
                TiffEncoderOptions options = new TiffEncoderOptions();
                options.setCompressionFormat(TiffCompressionFormat.DEFLATE_BETTER_COMPRESSION);

                for (BufferedImage img : images.testImage) {

                    JDeli.write(img, options, new File(WriteData.rootDir + "tif/" + images.names[count] + "-jdeli_better_comp.tif"));
                    count++;
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void JDeli_delfate(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("tiff")) {

            int count = 0;

            try {
                TiffEncoderOptions options = new TiffEncoderOptions();
                options.setCompressionFormat(TiffCompressionFormat.DEFLATE);

                for (BufferedImage img : images.testImage) {

                    JDeli.write(img, options, new File(WriteData.rootDir + "tif/" + images.names[count] + "-jdeli_deflate.tif"));
                    count++;
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void JDeli_better_speed(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("tiff")) {

            int count = 0;

            try {
                TiffEncoderOptions options = new TiffEncoderOptions();
                options.setCompressionFormat(TiffCompressionFormat.DEFLATE_BETTER_SPEED);

                for (BufferedImage img : images.testImage) {

                    JDeli.write(img, options, new File(WriteData.rootDir + "tif/" + images.names[count] + "-jdeli_Better_speed.tif"));
                    count++;
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void JDeli_jpeg(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("tiff")) {

            int count = 0;

            try {
                TiffEncoderOptions options = new TiffEncoderOptions();
                options.setCompressionFormat(TiffCompressionFormat.JPEG);

                for (BufferedImage img : images.testImage) {

                    JDeli.write(img, options, new File(WriteData.rootDir + "tif/" + images.names[count] + "-jdeli_JPEG.tif"));
                    count++;
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void JDeli(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("tiff")) {

            int count = 0;

            try {


                for (BufferedImage img : images.testImage) {

                    JDeli.write(img, new TiffEncoderOptions(), new File(WriteData.rootDir + "tif/" + images.names[count] + "-jdeli.tif"));
                    count++;
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void ImageIO(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByImageIO("tiff")) {

            int count = 0;

            for (BufferedImage img : images.testImage) {
                try {
                    ImageIOUtils.write(img, "TIF", new File(WriteData.rootDir + "tif/" + images.names[count] + "-imageio.tif"));
                    count++;
                } catch (Exception ex) {
                     ex.printStackTrace();
                }
            }
        }
    }
}
