package com.inventorymanager.project.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private Properties properties;

    private static final String CONFIG_FILE = "../resources/application.properties";

    private ConfigurationManager() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("File not found: " + CONFIG_FILE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading file", e);
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized(ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        String value = properties.getProperty(key);
        if(value != null) {
            try {
                return Integer.parseInt(value);
            } catch(NumberFormatException e) {
                throw new RuntimeException("Invalid key: " + key, e);
            }
        }
        throw new RuntimeException("Key not found: " + key);
    }

    public boolean getBooleanProperty(String key) {
        String value = properties.getProperty(key);
        if(value != null) {
            return Boolean.parseBoolean(value);
        }
        throw new RuntimeException("Key not found: " + key);
    }
}
