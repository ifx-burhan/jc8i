package com.camunda;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * reference: https://stackoverflow.com/questions/40835785/inject-application-properties-without-spring
 */
public class PropertiesLoader {
    public static Properties loadProperties() throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = PropertiesLoader.class
          .getClassLoader()
          .getResourceAsStream("application.properties");
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }
}
