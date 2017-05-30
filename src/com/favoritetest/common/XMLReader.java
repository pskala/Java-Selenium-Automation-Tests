package com.favoritetest.common;

import org.apache.commons.configuration2.XMLConfiguration;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.ImmutableNode;


public class XMLReader {

    private static Iterator<HierarchicalConfiguration<ImmutableNode>> iterator;
    private static HierarchicalConfiguration data;

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

    /**
     * method used to get credentials from configuration xml
     * @param file
     * @param key
     * @return 
     */
    public static void getListXMLtreeValues(String file, String key) {
        
        Configurations configs = new Configurations();

        try {

            XMLConfiguration config = configs.xml(file);
            List<HierarchicalConfiguration<ImmutableNode>> confs = config.configurationsAt(key);

            iterator = confs.iterator();

        } catch (ConfigurationException cex) {

        }

    }

    public static void next() {

        if (iterator.hasNext()) {

            data = iterator.next();

        }

    }

    public static boolean hasNext() {
        return (iterator != null && iterator.hasNext());
    }

    public static String getXMLtreeValue(String key) {
        return data.getString(key);
    }
    public static void getListXMLtreeValues(String key) {
        getListXMLtreeValues("config.xml", key);
    }

    public static String getValue(String key) {
        return getValue("config.xml", key);
    }
}
