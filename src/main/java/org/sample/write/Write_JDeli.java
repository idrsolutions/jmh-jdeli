/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package org.sample.write;

import org.sample.data.WriteData;
import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.bmp.options.BmpEncoderOptions;
import com.idrsolutions.image.jpeg.options.JpegEncoderOptions;
import com.idrsolutions.image.jpeg2000.options.Jpeg2000EncoderOptions;
import com.idrsolutions.image.jpeg2000.options.Jpeg2000OutputSubtype;
import com.idrsolutions.image.png.options.PngCompressionFormat;
import com.idrsolutions.image.png.options.PngEncoderOptions;
import java.awt.image.BufferedImage;
import java.io.File;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class Write_JDeli {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        volatile BufferedImage[] testImage = WriteData.getImages();
        volatile int count = testImage.length;
        volatile String[] names = WriteData.getNames();

    }

    static {
        new File(WriteData.rootDir).mkdirs();
        new File(WriteData.rootDir+"bmp").mkdirs();
        new File(WriteData.rootDir+"jpg").mkdirs();
        new File(WriteData.rootDir+"jpg2000").mkdirs();
        new File(WriteData.rootDir+"png").mkdirs();
        new File(WriteData.rootDir+"tif").mkdirs();
    }

    @Benchmark
    public void BMP(BenchmarkState images) {

        int count = 0;

        try {
            BmpEncoderOptions options = new BmpEncoderOptions();

            for (BufferedImage img : images.testImage) {
                JDeli.write(img, options, new File(WriteData.rootDir+"bmp/" + images.names[count] + "-jdeli.bmp"));
                count++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Benchmark
    public void JPG(BenchmarkState images) {

        int count = 0;

        try {
            JpegEncoderOptions options = new JpegEncoderOptions();

            for (BufferedImage img : images.testImage) {
                JDeli.write(img, options, new File(WriteData.rootDir+"jpg/" + images.names[count] + "-jdeli.jpg"));
                count++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Benchmark
    public void JP2(BenchmarkState images) {

        int count = 0;

        try {
            Jpeg2000EncoderOptions options = new Jpeg2000EncoderOptions();
            options.setOutputSubtype(Jpeg2000OutputSubtype.JP2);
            for (BufferedImage img : images.testImage) {
                JDeli.write(img, options, new File(WriteData.rootDir+"jpg2000/" + images.names[count] + "-jdeli.jp2"));
                count++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Benchmark
    public void JPS(BenchmarkState images) {

        int count = 0;

        try {
            Jpeg2000EncoderOptions options = new Jpeg2000EncoderOptions();
            options.setOutputSubtype(Jpeg2000OutputSubtype.JPX);
            for (BufferedImage img : images.testImage) {
                JDeli.write(img, options, new File(WriteData.rootDir+"jpg2000/" + images.names[count] + "-jdeli.jpx"));
                count++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Benchmark
    public void PNG_FAST(BenchmarkState images) {

        int count = 0;

        try {
            PngEncoderOptions options = new PngEncoderOptions();
            options.setCompressionFormat(PngCompressionFormat.ZLIB_BETTER_SPEED);

            for (BufferedImage img : images.testImage) {
                JDeli.write(img, options, new File(WriteData.rootDir+"png/" + images.names[count] + "-jdeli-fast.png"));
                count++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Benchmark
    public void PNG_COMPRESS(BenchmarkState images) {

        int count = 0;

        try {
            PngEncoderOptions options = new PngEncoderOptions();
            //options.setCompressionFormat(PngCompressionFormat.ZLIB_BETTER_COMPRESSION);

            for (BufferedImage img : images.testImage) {

                JDeli.write(img, options, new File(WriteData.rootDir+"png/" + images.names[count] + "-jdeli-compress.png"));
                count++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Benchmark
    public void PNG_QUANT(BenchmarkState images) {

        int count = 0;

        try {
            PngEncoderOptions options = new PngEncoderOptions();
            options.setCompressionFormat(PngCompressionFormat.QUANTISED8BIT);

            for (BufferedImage img : images.testImage) {

                JDeli.write(img, options, new File(WriteData.rootDir+"png/" + images.names[count] + "-jdeli-quant.png"));
                count++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Benchmark
    public void TIFF(BenchmarkState images) {

        int count = 0;

        try {
            PngEncoderOptions options = new PngEncoderOptions();
            options.setCompressionFormat(PngCompressionFormat.QUANTISED8BIT);

            for (BufferedImage img : images.testImage) {

                JDeli.write(img, options, new File(WriteData.rootDir+"tif/" + images.names[count] + "-jdeli.tif"));
                count++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
