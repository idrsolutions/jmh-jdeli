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

public class WriteData {

    static BufferedImage[] testImage;
    static String[] write_images;

    public static final String root = System.getProperty("user.dir");

    public static final String separator = System.getProperty("file.separator");

    public static final String images_for_write_tests = root + separator + "testImages" + separator + "images-for-read-tests" + separator + "writing" + separator;

    public static final String rootDir = root + separator + "output" + separator;

    public static BufferedImage[] setTestImages(){

        /*
         * data will be loaded on start of each warm-up iteration
         */
        final File[] listDirs = new File(images_for_write_tests).listFiles();
        final Map<String, BufferedImage> values = new HashMap<>();

        if (listDirs == null) {
            throw new RuntimeException("No directory with files");
        }
        for (File f : listDirs) {
            if (!f.getName().endsWith(".txt")) {
            try {
                BufferedImage img = JDeli.read(new File(images_for_write_tests + f.getName()));

                if (img != null) {
                    values.put(f.getName(), img);
                }

            } catch (Exception ex) {
                Logger.getLogger(WriteData.class.getName()).log(Level.SEVERE, null, ex);
            }}
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
         return testImage;
    }

    public static BufferedImage[] getImages() {

        return testImage;
    }

    public static String[] getNames() {
        return write_images;
    }

}
