/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package org.sample.read;

import com.idrsolutions.image.JDeli;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.sample.data.ImageType;
import org.sample.data.TestImageData;

/**
 * My test on PngSuite samples shows JDeli/ImageIO same speed and Apache over twice as slow
 * 
 */
public class PngReadComparison {
    
    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final static String[] pngFiles = TestImageData.getReadTestFiles(ImageType.PNG);      
       
    }
    
    @Benchmark
    public void JDeli(BenchmarkState images) {
       
        for (String pngFile : images.pngFiles) {
            try {
                BufferedImage img = JDeli.read(new File(pngFile));
            } catch (Exception ex) {
                //   System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void Apache(BenchmarkState images) {
        
        for (String pngFile : images.pngFiles) {
            try {
                BufferedImage img = Imaging.getBufferedImage(new File(pngFile));               
                
            } catch (Exception ex) {
                //   System.out.println(ex);
            }
        }
    }

    @Benchmark
    public void ImageIO(BenchmarkState images) {
        
        for (String pngFile : images.pngFiles) {
            try {
                BufferedImage img = ImageIO.read(new File(pngFile));
                // System.out.println(pngFile + " " +img);
            } catch (Exception ex) {
                // System.out.println(ex);
            }
        }
    }
}
