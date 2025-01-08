/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package read;

import com.idrsolutions.image.JDeli;
import com.traneptora.jxlatte.JXLDecoder;
import com.traneptora.jxlatte.JXLImage;
import data.ReadData;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import utils.ImageIOUtils;
import utils.SupportedImageFormats;


/**
 *
 */
public class JXL extends ReadTest {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final String[] filesToRead = Arrays.stream(ReadData.getReadTestFiles("jxl")).toArray(String[]::new);
    }

    /**
     * one test must be in this class to run correctly in IDEA
     *
     * @param images the data to use for testing
     */

    @Benchmark
    public void JDeli(read.JXL.BenchmarkState images, Blackhole bh) {

        if (SupportedImageFormats.isReadingSupportedByJDeli()) {
            for (String jpgxlFile : images.filesToRead) {
                try {
                    BufferedImage img = JDeli.read(new File(jpgxlFile));
                    assert img != null;
                    bh.consume(img);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Benchmark
    public void ImageIO(read.JXL.BenchmarkState images, Blackhole bh) {
        if (SupportedImageFormats.isReadingSupportedByImageIO()) {
            for (String imageFile : images.filesToRead) {
                try {
                    BufferedImage img = ImageIOUtils.read(ReadData.getType(), new File(imageFile));
                    assert img != null;
                    bh.consume(img);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Benchmark
    public void Jxlatte(read.JXL.BenchmarkState images, Blackhole bh) {

            for (String imageFile : images.filesToRead) {
                try {
                    InputStream input = Files.newInputStream(Paths.get(imageFile));

                    JXLDecoder decoder = new JXLDecoder(input);
                    JXLImage image = decoder.decode();
                    assert image != null;
                    bh.consume(image);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
