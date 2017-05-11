package com.favoritetest.common;

import org.apache.commons.configuration2.XMLConfiguration;
import java.io.File;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;


public class XMLReader {

    private XMLReader() {

    }

    /**
     * method used to get credentials from configuration xml
     * @param file
     * @param key
     * @return 
     */
    public static String getValue(String file, String key) {
        
        String value;
        Configurations configs = new Configurations();

        try {
            XMLConfiguration config = configs.xml(file);
            value = config.getString(key);

        } catch (ConfigurationException cex) {

            return cex.getMessage();
        }

        return value;
    }

    

    public static String getValue(String key) {
        return getValue("config.xml", key);
    }
}
