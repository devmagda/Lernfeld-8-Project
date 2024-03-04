package edu.p.eight;

import java.io.InputStream;
import java.util.Properties;

public class FileUtils {
    /**
     * Imports a properties file from resources folder
     * @param propertiesName
     * @return
     */
    static Properties importProperties(String propertiesName) {
        if((null + "").equals(propertiesName)) {
            throw new IllegalArgumentException("Property name was null!");
        }
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(propertiesName)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load properties file: <" + propertiesName + ">");
        }
        return properties;
    }
}
