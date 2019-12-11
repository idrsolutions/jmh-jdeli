/*
 * Sample code to test all possible ImageIO write options
 * using jmh.
 */
package org.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.ImagingConstants;
import org.apache.commons.imaging.formats.png.PngConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;

public class Write_ApacheCommons {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        volatile BufferedImage[] testImage = TestImageData.getImages();
        volatile String[] names = TestImageData.getNames();

    }

    static {
        new File(TestImageData.rootDir).mkdirs();
        new File(TestImageData.rootDir + "bmp").mkdirs();
        new File(TestImageData.rootDir + "png").mkdirs();
        new File(TestImageData.rootDir + "tif").mkdirs();
    }

    @Benchmark
    public void BMP(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                final Map<String, Object> params = new HashMap<>();
                Imaging.writeImage(img, new File(TestImageData.rootDir + "bmp/" + images.names[count] + "-apache.bmp"), ImageFormats.BMP, params);
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    

    @Benchmark
    public void PNG(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                final Map<String, Object> params = new HashMap<>();

                Imaging.writeImage(img, new File(TestImageData.rootDir + "png/" + images.names[count] + "-apache.png"), ImageFormats.PNG, params);
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void PNG_COMPRESSED(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                final Map<String, Object> params = new HashMap<>();

                params.put(ImagingConstants.PARAM_KEY_COMPRESSION, PngConstants.COMPRESSION_DEFLATE_INFLATE);
                Imaging.writeImage(img, new File(TestImageData.rootDir + "png/" + images.names[count] + "-apache_compressed.png"), ImageFormats.PNG, params);
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void TIFF(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                final Map<String, Object> params = new HashMap<>();
                params.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffConstants.TIFF_COMPRESSION_UNCOMPRESSED);

                Imaging.writeImage(img, new File(TestImageData.rootDir + "tif/" + images.names[count] + "-apache.tif"), ImageFormats.TIFF, params);
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void TIFF_1D(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                final Map<String, Object> params = new HashMap<>();
                params.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffConstants.TIFF_COMPRESSION_CCITT_GROUP_4);

                Imaging.writeImage(img, new File(TestImageData.rootDir + "tif/" + images.names[count] + "-apache_1D.tif"), ImageFormats.TIFF, params);
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void TIFF_GROUP3(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                final Map<String, Object> params = new HashMap<>();
                params.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffConstants.TIFF_COMPRESSION_CCITT_GROUP_3);

                Imaging.writeImage(img, new File(TestImageData.rootDir + "tif/" + images.names[count] + "-apache_group3.tif"), ImageFormats.TIFF, params);
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void TIFF_GROUP4(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                final Map<String, Object> params = new HashMap<>();
                params.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffConstants.TIFF_COMPRESSION_CCITT_GROUP_4);

                Imaging.writeImage(img, new File(TestImageData.rootDir + "tif/" + images.names[count] + "-apache_group4.tif"), ImageFormats.TIFF, params);
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void TIFF_LZW(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                final Map<String, Object> params = new HashMap<>();
                params.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffConstants.TIFF_COMPRESSION_LZW);

                Imaging.writeImage(img, new File(TestImageData.rootDir + "tif/" + images.names[count] + "-apache_lzw.tif"), ImageFormats.TIFF, params);
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void TIFF_PACKBITS(BenchmarkState images) {

        int count = 0;

        for (BufferedImage img : images.testImage) {
            try {
                final Map<String, Object> params = new HashMap<>();
                params.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffConstants.TIFF_COMPRESSION_PACKBITS);

                Imaging.writeImage(img, new File(TestImageData.rootDir + "tif/" + images.names[count] + "-apache_packbits.tif"), ImageFormats.TIFF, params);
                count++;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
