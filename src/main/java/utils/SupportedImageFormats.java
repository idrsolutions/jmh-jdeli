package utils;

import data.ReadData;

public class SupportedImageFormats {

    public static boolean isSupportedByApache() {

        switch (ReadData.getType()) {
            case "png":
                return true;
            case "jpg":
                return true;
            default:
                return false;
        }
    }

    public static boolean isSupportedByJDeli() {

        switch (ReadData.getType()) {
            case "png":
                return true;
            case "jpg":
                return true;
            default:
                return false;
        }
    }

    public static boolean isSupportedByImageIO() {

        switch (ReadData.getType()) {
            case "png":
                return true;
            case "jpg":
                return true;
            default:
                return false;
        }
    }
}
