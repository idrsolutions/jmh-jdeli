/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package org.sample.read;

import com.idrsolutions.image.JDeli;
import org.openjdk.jmh.annotations.Benchmark;
import org.sample.data.ReadData;
import org.sample.utils.ImageIOUtils;

import java.awt.image.BufferedImage;
import java.io.File;

public class ReadTest {

    @Benchmark
    public void JDeli(PNG.BenchmarkState images) {

        for (String pngFile : images.filesToRead) {
            try {
                BufferedImage img = JDeli.read(new File(pngFile));
            } catch (Exception ex) {
                //   System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void ImageIO(PNG.BenchmarkState images) {

        for (String pngFile : images.filesToRead) {
            try {
                BufferedImage img = ImageIOUtils.read(ReadData.getType(),new File(pngFile));
            } catch (Exception ex) {
                // System.out.println(ex);
            }
        }
    }
}
