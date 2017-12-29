package com.uic.oole.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties Utility
 * Provides utility method to load properties from file
 */
public class PropertiesUtils {

    static Properties prop = new Properties();
    static InputStream input = null;

    static{
        try {
            input = new FileInputStream("config.properties");
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get Properties
     * @return returns all the properties loaded from config file
     */
    public static Properties getProperties(){
        return prop;
    }

    public static void setProperty(String key, String value){
        prop.setProperty(key,value);
    }
}
