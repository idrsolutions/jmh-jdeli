/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package org.sample.read;

import org.sample.data.ImageType;
import com.idrsolutions.image.JDeli;
import java.awt.image.BufferedImage;
import java.io.File;
import org.openjdk.jmh.annotations.Benchmark;
import org.sample.data.TestImageData;

public class Read_JDeli {

    @Benchmark
    public void PNG() {

        final String[] pngFiles = TestImageData.getReadTestFiles(ImageType.PNG);
        int count = 0;

        for (String pngFile : pngFiles) {
            try {
                BufferedImage img = JDeli.read(new File(pngFile));
                if (img != null) {
                    count++;
                }
            } catch (Exception ex) {
                //   System.out.println(ex);
            }
        }

        System.out.println("JDeli reads " + count + " PNG files");

    }
}
