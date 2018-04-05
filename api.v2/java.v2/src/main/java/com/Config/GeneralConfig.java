package com.Config;

import com.webdriver.example.Utils.Log;

import java.io.FileInputStream;
import java.util.Properties;

public class GeneralConfig {

    public static final String PROPERTIES_PATH          = "src/main/resources/project.properties";
    public static final String URI                      = "URI";
    public static final String DEV_MAIL                 = "DEVELOPER_EMAIL";
    public static final String API_TOKEN                = "API_TOKEN";
    public static final String PROJECT_ID               = "PROJECT_ID";
    public static final String SET_NAME                 = "groups";


    protected static Properties m_props = new Properties();



    public static String getConfigurationValue(final String configurationName) {

        try{
            m_props.load(new FileInputStream(PROPERTIES_PATH));
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.error("Unable to load parameters");
        }
        return m_props.getProperty(configurationName);
    }

    public static boolean setConfigurationValue(final String configurationName, final String value) {
        return m_props.setProperty(configurationName, value) != null;
    }
}
