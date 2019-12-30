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
import org.sample.data.ImageType;
import org.sample.data.TestImageData;

/**
 * My test on PngSuite samples shows JDeli/ImageIO same speed and Apache over twice as slow
 * 
 * Results consistent if order of tests changed
 */
public class PngReadComparison {
    public static void main(String[] args) {
       
        
        final String[] pngFiles = TestImageData.getReadTestFiles(ImageType.PNG);      
        
        long now;
        
        now = System.currentTimeMillis();
        
        for (String pngFile : pngFiles) {
            try {
                BufferedImage img = ImageIO.read(new File(pngFile));
                // System.out.println(pngFile + " " +img);
            } catch (Exception ex) {
                // System.out.println(ex);
            }
        }
        
        System.out.println("ImageIO time taken = " +(System.currentTimeMillis()-now));
        
        now = System.currentTimeMillis();
        
        for (String pngFile : pngFiles) {
            try {
                BufferedImage img = Imaging.getBufferedImage(new File(pngFile));
                
            } catch (Exception ex) {
                //   System.out.println(ex);
            }
        }
        System.out.println("Apache time taken = " +(System.currentTimeMillis()-now));
        
        now = System.currentTimeMillis();
        
        
        for (String pngFile : pngFiles) {
            try {
                BufferedImage img = JDeli.read(new File(pngFile));               
            } catch (Exception ex) {
                //   System.out.println(ex);
            }
        }
        
        System.out.println("JDeli time taken = " +(System.currentTimeMillis()-now));
       
        
        
        
        
        
    }
}
