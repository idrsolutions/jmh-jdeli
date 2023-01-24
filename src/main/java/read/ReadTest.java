/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package read;

import com.idrsolutions.image.JDeli;
import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.Benchmark;
import data.ReadData;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;
import utils.ImageIOUtils;
import utils.SupportedImageFormats;

import java.awt.image.BufferedImage;
import java.io.File;

public class ReadTest {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void Apache(read.PNG.BenchmarkState images, Blackhole bh) {

        if (SupportedImageFormats.isSupportedByApache()) {
            for (String imageFile : images.filesToRead) {
                try {
                    BufferedImage img = Imaging.getBufferedImage(new File(imageFile));
                    bh.consume(img);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void ImageIO(read.HEIC.BenchmarkState images, Blackhole bh) {

        if (SupportedImageFormats.isSupportedByImageIO()) {
            for (String imageFile : images.filesToRead) {
                try {
                    BufferedImage img = ImageIOUtils.read(ReadData.getType(), new File(imageFile));
                    bh.consume(img);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
