package TestNG.tests;

import com.listeners.TestNGListenerForPractiTest;
import com.webdriver.example.Utils.Log;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestNGListenerForPractiTest.class)
public class SampleTests2 {

    @Test(description = "10")
    public void googleTest()
    {
        Log.info("Just checking");
    }

    @Test(description = "12")
    public void googleTest1()
    {
        Log.info("Just checking");
    }

    @Test(description = "13")
    public void googleTest2()
    {
        Log.info("Just 4");
    }

    @Test(description = "15")
    public void googleTest3()
    {
        Log.info("Just 6");
    }

    @Test(description = "17")
    public void googleTest4()
    {
        Log.info("Just 8");
    }

    @Test(description = "19")
    public void googleTest5()
    {
        Log.info("Just checking");
    }
}
