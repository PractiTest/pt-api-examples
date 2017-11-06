import api.practitest.PractiTestAPI;
import api.practitest.runs.StepModel;
import com.jayway.restassured.response.Response;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class RunWithTests {

    public final static void main(String[] args) throws Exception{

        String title = "PractiTest is an easy-to-use Test Management solution for professionals, allowing users to have all testing information";

        //Collect Test Steps
        Response getAllSteps = PractiTestAPI.sendGetSteps("84149");
        List list = getAllSteps.getBody().jsonPath().getList("data.attributes.name");

        //Create models for each step
        StepModel step1 = new StepModel();
        StepModel step2 = new StepModel();
        StepModel step3 = new StepModel();
        step1.setName(list.get(0).toString());
        step2.setName(list.get(1).toString());
        step3.setName(list.get(2).toString());


        //Create new Set
        List testIDs = new ArrayList();
        testIDs.add(84149);

        PractiTestAPI.sendCreateTestSet("new testing", testIDs);

        //Create new instance
        PractiTestAPI.sendCreateInstance(20803, 84149);

        //Setup chromeDriver
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();

        //Step1
        try
        {
            driver.get("http://www.google.com");
            step1.setStatus("PASSED");
        }
        catch (Exception e)
        {
            step1.setStatus("FAILED");
            e.printStackTrace();
        }
        //step 2
        try {
            driver.findElement(By.id("lst-ib")).sendKeys("check\n");
            step2.setStatus("PASSED");
        }
        catch (Exception e)
        {
            step2.setStatus("FAILED");
            e.printStackTrace();
        }
        //step 3
        try {
            driver.findElements(By.className("g")).contains(title);
            step3.setStatus("PASSED");
        }
        catch (Exception e)
        {
            step3.setStatus("FAILED");
            e.printStackTrace();
        }

        //Store all results
        List<StepModel> stepModel = new ArrayList();
        stepModel.add(step1);
        stepModel.add(step2);
        stepModel.add(step3);

        //Submit results
        PractiTestAPI.sendCreateRun(101263, stepModel);

    }
}
