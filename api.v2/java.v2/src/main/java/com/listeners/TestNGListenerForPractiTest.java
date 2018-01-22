package com.listeners;

import com.practitest.api.example.PractiTestWriter;
import com.practitest.integration.ExtractTests;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.List;

public class TestNGListenerForPractiTest implements ITestListener {


        @Override
        public void onTestStart(ITestResult result) {
        }

        @Override
        public void onTestSuccess(ITestResult result) {
        }

        @Override
        public void onTestFailure(ITestResult result) {
        }

        @Override
        public void onTestSkipped(ITestResult result) {
        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        }

        @Override
        public void onStart(ITestContext context) {
                List<Integer> testIDs = ExtractTests.extractAllTestIds(context);
                //Create test run for all tests in current execution
                Integer setID = PractiTestWriter.createNewSet(testIDs);
                //Store SetID for further usage
                System.setProperty("currentSetId", setID.toString());
                PractiTestWriter.createNewSet(testIDs);
        }


        @Override
        public void onFinish(ITestContext context) {
        }
}
