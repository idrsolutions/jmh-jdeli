/*
 * shared test code
 */
package data;

import com.idrsolutions.image.JDeli;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author markee
 */
public class WriteData {

    static BufferedImage[] testImage;
    static String[] write_images;
    static String[] png_files;

    public static String root = "/Users/markee/IdeaProjects/jmh-jdeli";

    public static String images_for_write_tests = root + "/testImages/images-for-write-tests/";

    public static String rootDir = root + "/output/";

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
                Logger.getLogger(WriteData.class.getName()).log(Level.SEVERE, null, ex);
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

    public static BufferedImage[] getImages() {

        return testImage;
    }

    public static String[] getNames() {
        return write_images;
    }

}
