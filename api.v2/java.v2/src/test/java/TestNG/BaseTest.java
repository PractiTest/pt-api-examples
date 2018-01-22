package TestNG;

import com.webdriver.example.DriverFactory;
import com.webdriver.example.Utils.Log;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeSuite
    public void beforeTestSuite()
    {

    }

    @BeforeTest
    public void beforeTest()
    {
        Log.startTestCase();
        DriverFactory.openChrome();
        DriverFactory.getBrowserInstance().manage().window().maximize();
    }

    @AfterTest
    public void afterTest()
    {
        DriverFactory.tearDown();
        Log.endTestCase();
    }

    @AfterSuite
    public void afterTestSuite()
    {

    }
}
