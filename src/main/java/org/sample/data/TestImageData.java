/*
 * shared test code
 */
package org.sample.data;

import com.idrsolutions.image.JDeli;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.sample.utils.ImageIOUtils;

/**
 *
 * @author markee
 */
public class TestImageData {

    static BufferedImage[] testImage;
    static String[] write_images;
    static String[] png_files, tiff_files;

    public static String images_for_write_tests = "/Users/markee/NetBeansProjects/jmh-jdeli/testImages/images-for-write-tests/";

    /**
     * good selection at http://www.schaik.com/pngsuite/
     */
    public static String png_images_for_read_tests = "/Users/markee/NetBeansProjects/jmh-jdeli/testImages/images-for-png-read-tests/";
    public static String tiff_images_for_read_tests = "/Users/markee/NetBeansProjects/jmh-jdeli/testImages/images-for-tiff-read-tests/";


    public static String rootDir = "/Users/markee/NetBeansProjects/jmh-jdeli/output/";

    static {

        /*
         * data will be loaded on start of each warm-up iteration
         */
        final File[] listDirs = new File(images_for_write_tests).listFiles();
        final Map<String, BufferedImage> values = new HashMap<>();

        for (File f : listDirs) {
            try {
                BufferedImage img = JDeli.read(new File(images_for_write_tests + f.getName()));

                if (img != null) {
                    values.put(f.getName(), img);
                }

            } catch (Exception ex) {
                Logger.getLogger(TestImageData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        final int size = values.keySet().size();

        testImage = new BufferedImage[size];
        write_images = new String[size];

        int i = 0;
        String file;
        for (String s : values.keySet()) {
            file = s;
            write_images[i] = file;
            testImage[i] = values.get(file);
            i++;

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
            case TIFF: //should only be read on the reload warm-up iteration
                if (tiff_files == null) {
                    tiff_files = getFileListFromDir(tiff_images_for_read_tests);
                }
                return png_files;
            default:
                return null;
        }
    }
}
