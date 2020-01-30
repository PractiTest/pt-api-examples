package com.webdriver.example;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    static{
        ChromeDriverManager.getInstance().arch64().setup();
    }


    private static ThreadLocal<WebDriver> driverContainer = new ThreadLocal<>();


    public static WebDriver getBrowserInstance(){
        return driverContainer.get();
    }

    public static WebDriver openChrome() {

        driverContainer.set(new ChromeDriver());
        return driverContainer.get();
    }

    public static void tearDown() {
        driverContainer.get().quit();
        driverContainer.set(null);
    }
}
