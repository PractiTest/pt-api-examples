package com.practitest.api.example;


import com.Config.GeneralConfig;
import com.jayway.restassured.response.Response;
import com.practitest.api.common.RequestFactory;
import com.practitest.api.model.instance.InstanceModel;
import com.practitest.api.model.runs.*;
import com.practitest.api.model.sets.Instances;
import com.practitest.api.model.sets.SetsModel;

import java.util.List;

public class PractiTestAPI {



    /**
     * Reads project.properties file for projectID
     */
    private static String projectID = GeneralConfig.getConfigurationValue(GeneralConfig.PROJECT_ID);

    /**
     *
     * @param id
     * @return
     */
    public static Response sendGetSteps(String id)
    {
        return RequestFactory.doGet("/api/v2/projects/"+projectID+"/steps.json?test-ids=" +id);
    }
//
//    /**
//     *
//     * @param body
//     */
//    public static void sendCreateRun(String body)
//    {
//        RequestFactory.doPost("/v2/projects/"+projectID+"/runs.json", body);
//    }

    /**
     *
     * @param instanceID
     * @param step
     * @return
     */
    public static Response sendCreateRun(String instanceID, List<StepModel> step)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID, 0));
        data.setSteps(new Steps(step));
        return RequestFactory.doPost("/api/v2/projects/"+projectID+"/runs.json", new RunsModel(data)).getBody().jsonPath().get("data.id");
    }

    /**
     *
     * @param instanceID
     * @return
     */
    public static Response sendCreateRun(String instanceID)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID, 0));
        return RequestFactory.doPost("/api/v2/projects/"+projectID+"/runs.json", new RunsModel(data)).getBody().jsonPath().get("data.id");
    }

    /**
     *
     * @param instanceID
     * @return
     */
    public static Response sendSubmitResult(String instanceID, int exitCode)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID, exitCode));
        Response response = RequestFactory.doPost("/api/v2/projects/"+projectID+"/runs.json", new RunsModel(data));
        response.prettyPrint();
        return response;
    }

    /**
     *
     * @param body
     */
    public static void sendCreateInstance(String body)
    {
        RequestFactory.doPost("/api/v2/projects/"+projectID+"/instances.json", body).prettyPrint();
    }

    public static Response sendGetInstances(String testSetID)
    {
        return RequestFactory.doGet("/api/v2/projects/"+projectID+"/instances.json?set-ids="+testSetID);
    }

    public static Response sendGetInstanceBytestID(Integer testID)
    {
        return RequestFactory.doGet("/api/v2/projects/"+projectID+"/instances.json?test-ids="+testID.toString());
    }

    public static Response sendGetInstanceByTestIDAndTestSetID(String testID, String testSetID)
    {
        Response response = RequestFactory.doGet("/api/v2/projects/"+projectID+"/instances.json?test-ids="+testID+"&set-ids="+testSetID);
        response.prettyPrint();
        return response;
    }

    public static Response sendRemoveInstance(Integer instanceID)
    {
        return RequestFactory.doDelete("/api/v2/projects/"+projectID+"/instances/"+instanceID.toString()+".json", "");
    }

    /**
     *
     * @param setID
     * @param testID
     * @return
     */
    public static Response sendCreateInstance(String setID, int testID)
    {
        com.practitest.api.model.instance.Data data = new com.practitest.api.model.instance.Data();
        data.setAttributes(new com.practitest.api.model.instance.Attributes(setID, testID));
        Response response = RequestFactory.doPost("/api/v2/projects/"+projectID+"/instances.json", new InstanceModel(data));
        response.prettyPrint();
        return response;
    }

    /**
     *
     * @param body
     * @return
     */
    public static Response sendCreateTestSet(String body)
    {
        return RequestFactory.doPost("/api/v2/projects/"+projectID+"/sets.json", body);
    }

    /**
     *
     * @return
     */
    public static Response sendGetTestSet()
    {
        return RequestFactory.doGet("/api/v2/projects/"+projectID+"/sets.json");
    }

    /**
     *
     * @return
     */
    public static Response sendGetTestSetByName(String nameExect)
    {
        return RequestFactory.doGet("/api/v2/projects/"+projectID+"/sets.json?name_like="+nameExect);
    }

    /**
     *
     * @param name
     * @param testIDs
     * @return
     */
    public static Response sendCreateTestSet(String name, List<Integer> testIDs)
    {
        com.practitest.api.model.sets.Data data = new com.practitest.api.model.sets.Data();
        data.setInstances(new Instances(testIDs));
        data.setAttributes(new com.practitest.api.model.sets.Attributes(name));
        Response response = RequestFactory.doPost("/api/v2/projects/"+projectID+"/sets.json", new SetsModel(data));
        response.prettyPrint();
        return response;
    }


}
