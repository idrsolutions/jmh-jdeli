/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package org.sample.write;

import org.sample.data.TestImageData;
import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.bmp.options.BmpEncoderOptions;
import com.idrsolutions.image.jpeg.options.JpegEncoderOptions;
import com.idrsolutions.image.jpeg2000.options.Jpeg2000EncoderOptions;
import com.idrsolutions.image.jpeg2000.options.Jpeg2000OutputSubtype;
import com.idrsolutions.image.png.options.PngCompressionFormat;
import com.idrsolutions.image.png.options.PngEncoderOptions;
import com.idrsolutions.image.process.ImageProcessingOperations;
import java.awt.image.BufferedImage;
import java.io.File;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class Test_JDeli_fileSize {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        volatile BufferedImage[] testImage = TestImageData.getImages();
        volatile int count = testImage.length;
        volatile String[] names = TestImageData.getNames();

    }

    static {
        new File(TestImageData.rootDir).mkdirs();
        new File(TestImageData.rootDir+"bmp").mkdirs();
        new File(TestImageData.rootDir+"jpg").mkdirs();
        new File(TestImageData.rootDir+"jpg2000").mkdirs();
        new File(TestImageData.rootDir+"png").mkdirs();
        new File(TestImageData.rootDir+"tif").mkdirs();
    }

    @Benchmark
    public void BMP(BenchmarkState images) {

        int count = 0;

        try {
            BmpEncoderOptions options = new BmpEncoderOptions();

            for (BufferedImage img : images.testImage) {
                
                JDeli.write(img, options, new File(TestImageData.rootDir+"bmp/" + images.names[count] + "-jdeli.bmp"));
                
                BufferedImage convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_INT_ARGB), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"bmp/" + images.names[count] + "-jdeli-argb.bmp"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_BINARY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"bmp/" + images.names[count] + "-jdeli-binary.bmp"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_GRAY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"bmp/" + images.names[count] + "-jdeli-gray.bmp"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_INDEXED), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"bmp/" + images.names[count] + "-jdeli-indexed.bmp"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toRGB(), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"bmp/" + images.names[count] + "-jdeli-rgb.bmp"));
                
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
                JDeli.write(img, options, new File(TestImageData.rootDir+"jpg/" + images.names[count] + "-jdeli.jpg"));
                
                BufferedImage convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_INT_ARGB), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg/" + images.names[count] + "-jdeli-argb.jpg"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_BINARY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg/" + images.names[count] + "-jdeli-binary.jpg"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_GRAY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg/" + images.names[count] + "-jdeli-gray.jpg"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_INDEXED), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg/" + images.names[count] + "-jdeli-indexed.jpg"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toRGB(), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg/" + images.names[count] + "-jdeli-rgb.jpg"));
                
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
                JDeli.write(img, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli.jp2"));
                
                BufferedImage convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_INT_ARGB), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-argb.jp2"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_BINARY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-binary.jp2"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_GRAY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-gray.jp2"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_INDEXED), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-indexed.jp2"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toRGB(), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-rgb.jp2"));
                
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
                JDeli.write(img, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli.jpx"));
                
                BufferedImage convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_INT_ARGB), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-argb.jpx"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_BINARY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-binary.jpx"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_GRAY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-gray.jpx"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_INDEXED), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-indexed.jpx"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toRGB(), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"jpg2000/" + images.names[count] + "-jdeli-rgb.jpx"));
                
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
                JDeli.write(img, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-fast.png"));
                
                BufferedImage convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_INT_ARGB), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-fast-argb.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_BINARY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-fast-binary.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_GRAY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-fast-gray.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_INDEXED), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-fast-indexed.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toRGB(), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-fast-rgb.png"));
                
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

                JDeli.write(img, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-compress.png"));
                
                BufferedImage convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_INT_ARGB), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-compress-argb.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_BINARY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-compress-binary.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_GRAY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-compress-gray.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_INDEXED), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-compress-indexed.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toRGB(), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-compress-rgb.png"));
                
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

                JDeli.write(img, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-quant.png"));
                
                BufferedImage convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_INT_ARGB), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-quant-argb.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_BINARY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-quant-binary.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_GRAY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-quant-gray.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_INDEXED), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-quant-indexed.png"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toRGB(), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"png/" + images.names[count] + "-jdeli-quant-rgb.png"));
                
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

                JDeli.write(img, options, new File(TestImageData.rootDir+"tif/" + images.names[count] + "-jdeli.tif"));
                
                BufferedImage convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_INT_ARGB), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"tif/" + images.names[count] + "-jdeli-argb.tif"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_BINARY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"tif/" + images.names[count] + "-jdeli-binary.tif"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_GRAY), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"tif/" + images.names[count] + "-jdeli-gray.tif"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toColorspace(BufferedImage.TYPE_BYTE_INDEXED), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"tif/" + images.names[count] + "-jdeli-indexed.tif"));
                
                convertedImg = JDeli.process(new ImageProcessingOperations().toRGB(), img);
                JDeli.write(convertedImg, options, new File(TestImageData.rootDir+"tif/" + images.names[count] + "-jdeli-rgb.tif"));
                
                count++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
