package Cucumber;

import com.webdriver.example.DriverFactory;
import com.webdriver.example.Utils.Log;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class Hooks {
    public static ThreadLocal<Scenario> lastScenario = new ThreadLocal<>();

    @Before
    public void openBrowser() {
        Log.startTestCase();
        DriverFactory.openChrome();
        DriverFactory.getBrowserInstance().manage().window().maximize();
    }


    @After
    public void afterScenario(Scenario scenario) {
        lastScenario.set(scenario);
        try {
            if (scenario.isFailed()) {
                try {
                    scenario.write("Current Page URL is " + DriverFactory.getBrowserInstance().getCurrentUrl());
                    byte[] screenshot = ((TakesScreenshot) DriverFactory.getBrowserInstance()).getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png");
                } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                    System.err.println(somePlatformsDontSupportScreenshots.getMessage());
                }
            }
        }finally {
            DriverFactory.tearDown();
            //if (TestRailWriter.isIsTestRailActive()) { TODO: implement practitest usage
             //   TestRailWriter.afterScenario(); TODO: implement after scenario case usage
           // }
            Log.endTestCase();
        }
    }
}
