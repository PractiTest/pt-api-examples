package TestNG.tests;

import TestNG.BaseTest;
import com.listeners.TestNGListenerForPractiTest;
import com.webdriver.example.Utils.Log;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListenerForPractiTest.class)
public class SampleTest  extends BaseTest{


    @Test(description = "1")
    public void googleTest()
    {
        Log.info("Just checking");
    }

    @Test(description = "2")
    public void googleTest1()
    {
        Log.info("Just checking");
    }

    @Test(description = "3")
    public void googleTest2()
    {
        Log.info("Just 4");
    }

    @Test(description = "5")
    public void googleTest3()
    {
        Log.info("Just 6");
    }

    @Test(description = "7")
    public void googleTest4()
    {
        Log.info("Just 8");
    }

    @Test(description = "9")
    public void googleTest5()
    {
        Log.info("Just checking");
    }
}
