/*
 * shared test code
 */
package org.sample.data;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author markee
 */
public class ReadData {

    static String[] images_to_read;

    public static final String root =System.getProperty("user.dir");

    public static final String images_for_read_tests = root+"/testImages/images-for-read-tests/";

    private static String type;

    private static String[] getFileListFromDir(final String path) {

        File[] listDirs = new File(path).listFiles();

        ArrayList<String> imageFiles = new ArrayList<>();
        if(listDirs == null) {
            throw new RuntimeException("No "+path+" directory with files");
        }
        for (File f : listDirs) {

            if(f.getName().endsWith(type)){
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
