/*
 * shared test code
 */
package org.sample;

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
class TestImageData {

    static BufferedImage[] testImage;
    static String[] names;

    static String images_for_write_tests= "/Users/markee/NetBeansProjects/jmh-jdeli/testImages/images-for-write-tests/";
    static String rootDir ="/Users/markee/NetBeansProjects/jmh-jdeli/output/";
    
    static {

            File[] listDirs = new File(images_for_write_tests).listFiles();
            
            testImage = new BufferedImage[listDirs.length];
            names = new String[listDirs.length];
            int i=0;
            for(File f: listDirs){
                try {
                    names[i]= f.getName();
                    testImage[i] = ImageIO.read(new File(images_for_write_tests+f.getName()));                   
                    i++;
                } catch (IOException ex) {
                    Logger.getLogger(TestImageData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    static BufferedImage[] getImages() {

        return testImage;
    }

    static String[] getNames() {
        return names;
    }
}
