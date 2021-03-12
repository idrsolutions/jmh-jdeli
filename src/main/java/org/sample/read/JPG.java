/*
 * Sample code to test all possible JDeli write options
 * using jmh. Requires JDeli trial or full jar as Maven dep
 */
package org.sample.read;

import org.apache.commons.imaging.Imaging;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.sample.data.ReadData;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 */
public class JPG extends ReadTest {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        final static String[] filesToRead = ReadData.getReadTestFiles("jpg");

    }

    @Benchmark
    public void Apache(BenchmarkState images) {

        for (String imageFile : images.filesToRead) {
            try {
                BufferedImage img = Imaging.getBufferedImage(new File(imageFile));

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
