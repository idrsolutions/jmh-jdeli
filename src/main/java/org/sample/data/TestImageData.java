/*
 * shared test code
 */
package org.sample.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author markee
 */
public class TestImageData {

    static BufferedImage[] testImage;
    static String[] write_images;
    static String[] png_files;

    public static String images_for_write_tests = "/Users/markee/NetBeansProjects/jmh-jdeli/testImages/images-for-write-tests/";
    
    /**
     * good selection at http://www.schaik.com/pngsuite/
     */
    public static String png_images_for_read_tests = "/Users/markee/NetBeansProjects/jmh-jdeli/testImages/images-for-png-read-tests/";
    
    public static String rootDir = "/Users/markee/NetBeansProjects/jmh-jdeli/output/";

    static {

        File[] listDirs = new File(images_for_write_tests).listFiles();

        testImage = new BufferedImage[listDirs.length];
        write_images = new String[listDirs.length];
        int i = 0;
        for (File f : listDirs) {
            try {
                write_images[i] = f.getName();
                testImage[i] = ImageIO.read(new File(images_for_write_tests + f.getName()));
                i++;
            } catch (IOException ex) {
                Logger.getLogger(TestImageData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static String[] getFileListFromDir(final String path) {
        File[] listDirs;
        listDirs = new File(path).listFiles();
        final String[] list_of_files = new String[listDirs.length];
        int i = 0;
        for (File f : listDirs) {

            list_of_files[i] = path + f.getName();
            i++;

        }

        return list_of_files;
    }

    public static BufferedImage[] getImages() {

        return testImage;
    }

    public static String[] getNames() {
        return write_images;
    }

    public static String[] getReadTestFiles(org.sample.data.ImageType imageType) {

        switch (imageType) {
            case PNG: //should only be read on the reload warm-up iteration
                if (png_files == null) {
                    png_files = getFileListFromDir(png_images_for_read_tests);
                }
                return png_files;
            default:
                return null;
        }
    }
}
