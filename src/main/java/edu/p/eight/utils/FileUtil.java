package edu.p.eight.utils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class FileUtil {
    private static String cachedFolder = null;
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


    public static void saveProperties(Properties properties) {
        try {
            // Change the path to the location where you want to save the properties file
            FileOutputStream outputStream = new FileOutputStream(getConfigPath());
            properties.store(outputStream, "Highscore");
            outputStream.close();
            System.out.println("Properties saved successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save properties: " + e.getMessage());
        }
    }

    public static Properties getConfigFile() throws FileNotFoundException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        String filePath = getConfigPath();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                properties.load(fileInputStream);
                return properties;
            } else {
                throw new FileNotFoundException("Properties file could not be found");
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Properties file could not be found");
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("Could not close fileInputStream for file:" + filePath);
                }
            }
        }
    }

    public static String getBasePath() {
        if(cachedFolder == null) {
            File basePath = new File("src/main/resources");
            if(basePath.exists() && !basePath.isFile()) {
                cachedFolder = "src/main/resources/";
            } else {
                cachedFolder = "resources/";
            }
        }
        return cachedFolder;
    }

    public static String getConfigPath() {
        return getBasePath() + "config.properties";
    }
}
