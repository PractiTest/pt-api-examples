package com.practitest.api.example;

import com.Config.GeneralConfig;
import com.jayway.restassured.response.Response;
import com.practitest.api.model.runs.StepModel;

import java.util.List;

public class PractiTestWriter {

    /**
     *
     * @return set id for the project
     */
    public static String getSetID(String setName)
    {

            Response response = PractiTestAPI.sendGetTestSetByName(setName);
            response.prettyPrint();
            try
            {
                return response.getBody().jsonPath().get("data.id");
            }
            catch (Exception e)
            {
                return null;
            }
    }

    /**
     *
     * @param testIDs List of tests which should be assigned to this Test Set
     * @return new TestSetID
     */
    public static String createNewSet(List<Integer> testIDs)
    {
        Response response = PractiTestAPI.sendCreateTestSet(GeneralConfig.getConfigurationValue("groups"), testIDs);//System.getProperty("groups"), testIDs); //
        response.prettyPrint();
        return response.getBody().jsonPath().get("data.id") ;
    }

    /**
     *
     * @param setID TestSet ID which should be used for new Instance
     * @param testID TestCase ID which should be used to create new instance
     * @return new Instance ID
     */
    public static String createNewInstance(String setID, Integer testID)
    {
        return PractiTestAPI.sendCreateInstance(setID, testID).getBody().jsonPath().get("data.id");
    }

    public static void createAllInstances(String setID, List<Integer> testID)
    {
        for (int i =0; i<testID.size();i++)
        {
            createNewInstance(setID, testID.get(i));
        }

//        for (Integer aTestID : testID) {
//            createNewInstance(setID, aTestID);
//        }
    }

    public static List<Integer> getInstancesByTestSetID(String testSet)
    {
        return PractiTestAPI.sendGetInstances(testSet).body().jsonPath().get("data.id");
    }

    public static Integer getInstancesByTestID(Integer testID)
    {
        return PractiTestAPI.sendGetInstanceBytestID(testID).body().jsonPath().get("data.id");
    }

    public static List<String> getInstancesByTestIDAndTestSetID(String testID, String testSetID)
    {
        return PractiTestAPI.sendGetInstanceByTestIDAndTestSetID(testID, testSetID).body().jsonPath().get("data.id");
    }

    public static List<Integer> getTestIDsForTestSetID(String testSet)
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

    public static String submitResults(String instanceID, Integer statusCode)
    {
        return PractiTestAPI.sendSubmitResult(instanceID, statusCode).getBody().jsonPath().get("data.id");
    }

    public static String submitResults(String instanceID, String error, Integer statusCode)
    {
        return PractiTestAPI.sendSubmitResult(instanceID, error, statusCode).getBody().jsonPath().get("data.id");
    }

}
