/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package write;

import data.WriteData;
import com.idrsolutions.image.JDeli;
import com.idrsolutions.image.jpeg2000.options.Jpeg2000EncoderOptions;
import com.idrsolutions.image.jpeg2000.options.Jpeg2000OutputSubtype;

import java.awt.image.BufferedImage;
import java.io.File;

import org.openjdk.jmh.annotations.*;
import utils.ImageIOUtils;
import utils.SupportedImageFormats;

public class JPX {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final BufferedImage[] testImage = WriteData.setTestImages();
        final String[] names = WriteData.getNames();

    }

    static {
        new File(WriteData.rootDir).mkdirs();
        new File(WriteData.rootDir + "jpg2000").mkdirs();
    }

    @Benchmark
    public void JDeli_JP2(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("jp2")) {

            int count = 0;

            try {
                Jpeg2000EncoderOptions options = new Jpeg2000EncoderOptions();
                options.setOutputSubtype(Jpeg2000OutputSubtype.JP2);
                for (BufferedImage img : images.testImage) {
                    JDeli.write(img, options, new File(WriteData.rootDir + "jpg2000/" + images.names[count] + "-jdeli.jp2"));
                    count++;
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    public void JDeli_JPS(BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByJDeli("jp2")) {
            int count = 0;

            try {
                Jpeg2000EncoderOptions options = new Jpeg2000EncoderOptions();
                options.setOutputSubtype(Jpeg2000OutputSubtype.JPX);
                for (BufferedImage img : images.testImage) {
                    JDeli.write(img, options, new File(WriteData.rootDir + "jpg2000/" + images.names[count] + "-jdeli.jpx"));
                    count++;
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @Benchmark
    public void ImageIO(JPX.BenchmarkState images) {
        if (SupportedImageFormats.isWritingSupportedByImageIO("jp2")) {

            int count = 0;

            for (BufferedImage img : images.testImage) {
                try {
                    ImageIOUtils.write(img, "JP2", new File(WriteData.rootDir + "jpg2000/" + images.names[count] + "-imageio.jp2"));
                    count++;
                } catch (Exception ex) {
                     ex.printStackTrace();
                }
            }
        }
    }
}
