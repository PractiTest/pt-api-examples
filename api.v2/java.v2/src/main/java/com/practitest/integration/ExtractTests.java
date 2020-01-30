package com.practitest.integration;

import com.webdriver.example.Utils.Log;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExtractTests {

    public static List<Integer> extractAllTestIds(ITestContext context)
    {
        ITestNGMethod[] testMethods = context.getAllTestMethods();
        List<String> testIds = new ArrayList(0);
        if (testMethods.length == 0)
        {
            Log.error("PLEASE PROVIDE VALID GROUP NAME");
            Assert.fail("No test methods found for specified group");
        }
        for (ITestNGMethod testMethod : testMethods) {
            testIds.add(testMethod.getDescription());
        }
        return testIds.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

}
