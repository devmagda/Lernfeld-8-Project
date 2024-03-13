package edu.p.eight.utils;

import org.h2.util.IOUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    public static List<File> getFilesInDirectory(String folder) {
        if(folder != null) {
            // String was prefixed with "file:\" before, so we simply remove the first 6 characters;
            // String path = FileUtil.class.getResource("/" + folder).toString();//.substring(6);

            File file = new File(folder);
            if(!file.isFile()) {
                File[] list = file.listFiles();
                if(list == null) {
                    throw new RuntimeException("Path <" + folder + "> did not list any files ..");
                }
                return Arrays.asList(list);
            }
        }
        throw new RuntimeException("Folder <" + folder +"> is a file");
    }

    public static File getConfigFile() {
        return new File("config.properties");
    }
}
