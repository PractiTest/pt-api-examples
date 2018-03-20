package com.practitest.api.example;

import com.jayway.restassured.response.Response;
import com.practitest.api.model.runs.StepModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PractiTestWriter {

    /**
     *
     * @return Generates name for new test run with time stamp
     */
    private static String getNameForNewRun() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String testSuite = System.getProperty("test");
        return testSuite + " " + dateFormat.format(date);
    }

    /**
     *
     * @return set id for the project
     */
    public static Integer getSetID(String setName)
    {
        Response response = PractiTestAPI.sendGetTestSetByName(setName);
        try
        {
            return response.getBody().jsonPath().get("data.id");
        }
        catch (Exception e)
        {
          return 0;
        }
    }

    /**
     *
     * @param testIDs List of tests which should be assigned to this Test Set
     * @return new TestSetID
     */
    public static Integer createNewSet(List<Integer> testIDs)
    {
        Response response = PractiTestAPI.sendCreateTestSet(getNameForNewRun(), testIDs);
        return PractiTestAPI.sendCreateTestSet(getNameForNewRun(), testIDs).getBody().jsonPath().get("data.id") ;
    }

    /**
     *
     * @param setID TestSet ID which should be used for new Instance
     * @param testID TestCase ID which should be used to create new instance
     * @return new Instance ID
     */
    public static Integer createNewInstance(Integer setID, Integer testID)
    {
        return PractiTestAPI.sendCreateInstance(setID, testID).getBody().jsonPath().get("data.id");
    }

    public static void createAllInstances(Integer setID, List<Integer> testID)
    {
        for (Integer aTestID : testID) {
            createNewInstance(setID, aTestID);
        }
    }

    public static List<Integer> getInstancesByTestSetID(Integer testSet)
    {
        return PractiTestAPI.sendGetInstances(testSet).body().jsonPath().get("data.id");
    }

    public static Integer getInstancesByTestID(Integer testID)
    {
        return PractiTestAPI.sendGetInstanceBytestID(testID).body().jsonPath().get("data.id");
    }

    public static String getInstancesByTestIDAndTestSetID(String testID, Integer testSetID)
    {
        return PractiTestAPI.sendGetInstanceByTestIDAndTestSetID(testID, testSetID).body().jsonPath().get("data.id");
    }

    public static List<Integer> getTestIDsForTestSetID(Integer testSet)
    {
        return PractiTestAPI.sendGetInstances(testSet).body().jsonPath().get("data.attributes.test-id");
    }

    public static void removeInstance(Integer instanceID)
    {
        PractiTestAPI.sendRemoveInstance(instanceID);
    }

    /**
     *
     * @param instanceID instance ID for test execution
     * @param stepModel step Model which was used for this test
     * @return extracts result ID for further usage
     */
    public static Integer submitResults(String instanceID, List<StepModel> stepModel)
    {
        return PractiTestAPI.sendCreateRun(instanceID, stepModel).getBody().jsonPath().get("get.id");
    }

    public static Integer submitResults(String instanceID, Integer statusCode)
    {
        return PractiTestAPI.sendSubmitResult(instanceID, statusCode).getBody().jsonPath().get("data.id");
    }

}
