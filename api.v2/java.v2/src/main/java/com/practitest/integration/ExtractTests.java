package com.practitest.integration;

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
        for (ITestNGMethod testMethod : testMethods) {
            testIds.add(testMethod.getDescription());
        }
        return testIds.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

}
