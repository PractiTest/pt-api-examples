package com.listeners;

import com.Config.GeneralConfig;
import com.practitest.api.example.PractiTestWriter;
import com.practitest.integration.ExtractTests;
import com.webdriver.example.Utils.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.List;

public class TestNGListenerForPractiTest implements ITestListener {

        protected String setID = null;


        @Override
        public void onTestStart(ITestResult result) {
        }

        @Override
        public void onTestSuccess(ITestResult result) {
                List<String> instanceID = PractiTestWriter.getInstancesByTestIDAndTestSetID(result.getMethod().getDescription(), this.setID);
                PractiTestWriter.submitResults(instanceID.get(0), 0);
        }

        @Override
        public void onTestFailure(ITestResult result) {
                List<String> instanceID = PractiTestWriter.getInstancesByTestIDAndTestSetID(result.getMethod().getDescription(), this.setID);
                PractiTestWriter.submitResults(instanceID.get(0), 1);
        }

        @Override
        public void onTestSkipped(ITestResult result) {
        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        }

        @Override
        public void onStart(ITestContext context) {
                String existingTestSetID = PractiTestWriter.getSetID(GeneralConfig.getConfigurationValue("groups"));//System.getProperty("groups"));
                //extract all tests for current execution
                List<Integer> testIDs = ExtractTests.extractAllTestIds(context);
                if (existingTestSetID == null)
                {
                       Log.info("Creating new test set");
                       this.setID = PractiTestWriter.createNewSet(testIDs);
                }
                else
                {
                        this.setID = existingTestSetID;
                        //get test id's for existing TestSet
                        List<Integer> currentTestSetTestIDs = PractiTestWriter.getTestIDsForTestSetID(this.setID);
                        //Remove existing test IDs from List to get removed
                        currentTestSetTestIDs.removeAll(testIDs);
                        //Remove Instance IDs
                        for (Integer currentTestSetTestID : currentTestSetTestIDs) {
                                PractiTestWriter.removeInstance(PractiTestWriter.getInstancesByTestID(currentTestSetTestID));
                        }
                        Log.info("Using existing TestSEtID: "+existingTestSetID);
                }
                //Create new instances for all tests in TestSet
                PractiTestWriter.createAllInstances(this.setID, testIDs);
                String current = PractiTestWriter.getSetID("test2");
                Log.info(current);
        }


        @Override
        public void onFinish(ITestContext context) {
        }
}
