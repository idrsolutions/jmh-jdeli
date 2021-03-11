/*
 * shared test code
 */
package org.sample.data;

import com.idrsolutions.image.JDeli;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author markee
 */
public class ReadData {

    static String[] images_to_read;

    public static String root ="/Users/markee/IdeaProjects/jmh-jdeli";

    public static String images_for_write_tests = root+"/testImages/images-for-write-tests/";

    /**
     * good selection at http://www.schaik.com/pngsuite/
     */
    public static String images_for_read_tests = root+"/testImages/images-for-read-tests/";

    private static String type;

    private static String[] getFileListFromDir(final String path) {

        File[] listDirs = new File(path).listFiles();

        ArrayList<String> imageFiles = new ArrayList<>();
        for (File f : listDirs) {

            if(f.getName().endsWith(type.toString().toLowerCase())){
                imageFiles.add(path + f.getName());
            }
        }

        return imageFiles.toArray(new String[0]);
    }

    public static String[] getReadTestFiles(String imageType) {

        type = imageType;

        if (images_to_read == null) {
            System.out.println("read images for "+type);
            images_to_read = getFileListFromDir(images_for_read_tests+type);
        }

        return images_to_read;

    }

    public static String getType() {
        return type;
    }
}
