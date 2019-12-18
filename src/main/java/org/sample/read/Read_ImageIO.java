/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package org.sample.read;

import org.sample.data.ImageType;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.openjdk.jmh.annotations.Benchmark;
import org.sample.data.TestImageData;

public class Read_ImageIO {

    @Benchmark
    public void PNG() {

        final String[] pngFiles = TestImageData.getReadTestFiles(ImageType.PNG);
        int count = 0;
        for (String pngFile : pngFiles) {
            try {
                BufferedImage img = ImageIO.read(new File(pngFile));

                if (img != null) {
                    count++;
                }
                // System.out.println(pngFile + " " +img);
            } catch (Exception ex) {
                // System.out.println(ex);
            }
        }

        System.out.println("ImageIO reads " + count + " PNG files");
    }
}
