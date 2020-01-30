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


    protected static Properties m_props = new Properties();
    private static boolean m_initialized = false;

    // SINGLETON CONSTRUCTOR

    GeneralConfig() {}

    private static ThreadLocal<GeneralConfig> instanceContainer = new ThreadLocal<GeneralConfig>(){
        @Override
        protected GeneralConfig initialValue() {
            return new GeneralConfig();
        }
    };

    public static GeneralConfig getInstance() {
        return instanceContainer.get();
    }


    public static boolean isInitialized() {
        return m_initialized;
    }

    public static void loadConfig()
    {
        try{
            m_props.load(new FileInputStream(PROPERTIES_PATH));
            m_initialized = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.error("Unable to load parameters");
        }
    }



    public static String getConfigurationValue(final String configurationName) {
        if(!GeneralConfig.isInitialized())
            throw new IllegalStateException("GeneralConfig is not initialized, please call GeneralConfig.loadConfig()");

        return m_props.getProperty(configurationName);
    }

    /*
    Should be used if any parameter should be modified during test execution
     */
    public static boolean setConfigurationValue(final String configurationName, final String value) {
        return m_props.setProperty(configurationName, value) != null;
    }
}
