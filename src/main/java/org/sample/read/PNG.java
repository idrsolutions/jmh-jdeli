/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package org.sample.read;

import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.sample.data.ReadData;

/**
 * My test on PngSuite samples shows JDeli/ImageIO same speed and Apache over twice as slow
 *
 */
public class PNG extends ReadTest {
    
    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final static String[] filesToRead = ReadData.getReadTestFiles("png");
       
    }

    @Benchmark
    public void Apache(BenchmarkState images) {
        
        for (String pngFile : images.filesToRead) {
            try {
                BufferedImage img = Imaging.getBufferedImage(new File(pngFile));               
                
            } catch (Exception ex) {
                //   System.out.println(ex);
            }
        }
    }
}
