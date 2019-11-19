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

    static String rootDir ="/Users/markee/Downloads/test/";
    
    static {

            File[] listDirs = new File("/Users/markee/Downloads/5120x2880/8BIT/COLOR/").listFiles();
            
            testImage = new BufferedImage[listDirs.length];
            names = new String[listDirs.length];
            int i=0;
            for(File f: listDirs){
                try {
                    names[i]= f.getName();
                    testImage[i] = ImageIO.read(new File("/Users/markee/Downloads/5120x2880/8BIT/COLOR/"+f.getName()));
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
