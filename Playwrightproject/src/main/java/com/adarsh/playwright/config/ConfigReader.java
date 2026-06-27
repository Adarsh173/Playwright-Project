package com.adarsh.playwright.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class
        .getClassLoader()
        .getResourceAsStream("config.properties")) {

    if (inputStream == null) {
        throw new RuntimeException("config.properties not found");
    }

    properties.load(inputStream);

        } catch (IOException e) {
    throw new RuntimeException("Unable to load config.properties", e);
}
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

    public static String getUrl() {
        return properties.getProperty("url");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}