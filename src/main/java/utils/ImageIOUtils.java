/*
 * Util class to bypass any JDeli plugin when we call ImageIO
 */
package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.jpedal.utils.LogWriter;

/**
 * @author markee
 */
public class ImageIOUtils {

    public static void write(BufferedImage img, String ext, File imageIOFile) {

        //write out using first non-IDR writer found
        final Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix(ext);
        boolean imageWritten = false;
        while (!imageWritten && writers.hasNext()) {
            final ImageWriter writer = writers.next();
            if (!"IDRSolutions".equals(writer.getOriginatingProvider().getVendorName())) {

                //System.out.println("Using " + writer.getOriginatingProvider().getVendorName());
                try (FileOutputStream fos = new FileOutputStream(imageIOFile); ImageOutputStream ios = ImageIO.createImageOutputStream(fos)) {
                    writer.setOutput(ios);
                    writer.write(img);
                } catch (final Exception e) {
                    e.printStackTrace(System.out);
                    System.out.println("ImageIO failed for " + imageIOFile);
                    LogWriter.writeLog(e);
                }
                imageWritten = true;
            }
        }
    }

    public static BufferedImage read(String ext, File imageIOFile) {

        //write out using first non-IDR writer found
        final Iterator<ImageReader> readers = ImageIO.getImageReadersBySuffix(ext);
        boolean imageWritten = false;
        while (!imageWritten && readers.hasNext()) {
            final ImageReader reader = readers.next();
            if (!"IDRSolutions".equals(reader.getOriginatingProvider().getVendorName())) {

                //System.out.println("Using " + reader.getOriginatingProvider().getVendorName());
                try (FileInputStream fos = new FileInputStream(imageIOFile); ImageInputStream ios = ImageIO.createImageInputStream(fos)) {
                    reader.setInput(ios);

                    return reader.read(0);

                } catch (final Exception e) {
                    e.printStackTrace(System.out);
                    System.out.println("ImageIO failed for " + imageIOFile);
                    LogWriter.writeLog(e);
                }
                imageWritten = true;
            }
        }

        return null;
    }
}
