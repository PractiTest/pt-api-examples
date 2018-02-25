package com.listeners;

import com.practitest.api.example.PractiTestWriter;
import com.practitest.integration.ExtractTests;
import com.webdriver.example.Utils.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.List;

public class TestNGListenerForPractiTest implements ITestListener {

        protected Integer instanceID = null;


        @Override
        public void onTestStart(ITestResult result) {
        }

        @Override
        public void onTestSuccess(ITestResult result) {
                PractiTestWriter.submitResults(instanceID, 0);
        }

        @Override
        public void onTestFailure(ITestResult result) {
                PractiTestWriter.submitResults(instanceID, 1);
        }

        @Override
        public void onTestSkipped(ITestResult result) {
        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        }

        @Override
        public void onStart(ITestContext context) {
                Integer existingTestID = PractiTestWriter.getSetID();
                if (existingTestID == 0)
                {
                        List<Integer> testIDs = ExtractTests.extractAllTestIds(context);
                        //Create test run for all tests in current execution
                        Integer setID = PractiTestWriter.createNewSet(testIDs);
                        //Create new instance
                        this.instanceID = PractiTestWriter.createNewInstance(setID, testIDs);
                }
                else
                {
                        Log.info("Using existing TestSEtID: "+existingTestID.toString());
                }

        }


        @Override
        public void onFinish(ITestContext context) {
        }
}
