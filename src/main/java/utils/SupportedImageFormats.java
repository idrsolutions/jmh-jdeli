package utils;

import data.ReadData;
import data.WriteData;

public class SupportedImageFormats {

    public static boolean isSupportedByApache() {

        switch (ReadData.getType()) {
            case "png":
            case "jpg":
            case "jpeg":
            case "tiff":
            case "gif":
            case "bmp":
                return true;
            default:
                return false;
        }
    }

    public static boolean isSupportedByJDeli() {

        switch (ReadData.getType()) {
            case "bmp":
            case "gif":
            case "heic":
            case "jp2":
            case "jpg":
            case "jpeg":
            case "png":
            case "tiff":
                return true;
            default:
                return false;
        }
    }

    public static boolean isSupportedByImageIO() {

        switch (ReadData.getType()) {
            case "bmp":
            case "gif":
            case "jpg":
            case "jpeg":
            case "png":
            case "tiff":
                return true;
            default:
                return false;
        }
    }

    public static boolean isWritingSupportedByApache(String format) {

        switch (format) {
            case "bmp":
            case "gif":
            case "png":
            case "tiff":
                return true;
            default:
                return false;
        }
    }

    public static boolean isWritingSupportedByJDeli(String format) {

        switch (format) {
            case "bmp":
            case "gif":
            case "heic":
            case "jp2":
            case "jpg":
            case "jpeg":
            case "png":
            case "tiff":
                return true;
            default:
                return false;
        }
    }

    public static boolean isWritingSupportedByImageIO(String format) {

        switch (format) {
            case "bmp":
            case "gif":
            case "jp2":
            case "jpg":
            case "jpeg":
            case "png":
            case "tiff":
                return true;
            default:
                return false;
        }
    }
}
