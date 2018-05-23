package com.j8.io.zip;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public final class zipUtils {

    private  zipUtils(){}

    public static boolean createZip(String needZipFolderPath,String zipNamePath) {
        try {
            ZipFile zipFile = new ZipFile(zipNamePath);

            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            zipFile.addFolder(needZipFolderPath, parameters);

            return  true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public static void main(String[] args) {
        String needZipFolderPath = "/home/mutian/Desktop/pdf";
        String zipNamePath = "/home/mutian/Desktop/AddFolder.zip";
        createZip(needZipFolderPath,zipNamePath);
    }
}
