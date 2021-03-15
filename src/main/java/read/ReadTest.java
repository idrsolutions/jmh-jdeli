/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package read;

import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.Benchmark;
import data.ReadData;
import utils.ImageIOUtils;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;

public class ReadTest {

    @Benchmark
    public void Apache(PNG.BenchmarkState images) {

        if (SupportedImageFormats.isSupportedByApache()) {
            for (String imageFile : images.filesToRead) {
                try {
                    BufferedImage img = Imaging.getBufferedImage(new File(imageFile));

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    @Benchmark
    public void ImageIO(PNG.BenchmarkState images) {

        if (SupportedImageFormats.isSupportedByImageIO()) {
            for (String pngFile : images.filesToRead) {
                try {
                    BufferedImage img = ImageIOUtils.read(ReadData.getType(), new File(pngFile));
                } catch (Exception ex) {
                    // System.out.println(ex);
                }
            }
        }
    }
}
