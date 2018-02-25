package com.practitest.api.example;


import com.practitest.api.common.RequestFactory;
import com.practitest.api.model.instance.InstanceModel;
import com.practitest.api.model.runs.*;
import com.practitest.api.model.sets.Instances;
import com.practitest.api.model.sets.SetsModel;
import com.jayway.restassured.response.Response;

import java.util.List;

public class PractiTestAPI {

    /**
     * Reads project.properties file for projectID
     */
    private final static  String projectID = System.getProperty("PROJECT_ID");

    /**
     *
     * @param id
     * @return
     */
    public static Response sendGetSteps(String id)
    {
        return RequestFactory.doGet("/v2/projects/"+projectID+"/steps.json?test-ids=" +id);
    }

    /**
     *
     * @param body
     */
    public static void sendCreateRun(String body)
    {
        RequestFactory.doPost("/v2/projects/"+projectID+"/runs.json", body);
    }

    /**
     *
     * @param instanceID
     * @param step
     * @return
     */
    public static Response sendCreateRun(int instanceID, List<StepModel> step)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID, 0));
        data.setSteps(new Steps(step));
        return RequestFactory.doPost("/v2/projects/"+projectID+"/runs.json", new RunsModel(data)).getBody().jsonPath().get("data.id");
    }

    /**
     *
     * @param instanceID
     * @return
     */
    public static Response sendCreateRun(int instanceID)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID, 0));
        return RequestFactory.doPost("/v2/projects/"+projectID+"/runs.json", new RunsModel(data)).getBody().jsonPath().get("data.id");
    }

    /**
     *
     * @param instanceID
     * @return
     */
    public static Response sendSubmitResult(int instanceID, int exitCode)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID, exitCode));
        return RequestFactory.doPost("/v2/projects/"+projectID+"/runs.json", new RunsModel(data)).getBody().jsonPath().get("data.id");
    }

    /**
     *
     * @param body
     */
    public static void sendCreateInstance(String body)
    {
        RequestFactory.doPost("/v2/projects/"+projectID+"/instances.json", body).prettyPrint();
    }

    /**
     *
     * @param setID
     * @param testID
     * @return
     */
    public static Response sendCreateInstance(int setID, int testID)
    {
        com.practitest.api.model.instance.Data data = new com.practitest.api.model.instance.Data();
        data.setAttributes(new com.practitest.api.model.instance.Attributes(setID, testID));

        return RequestFactory.doPost("/v2/projects/"+projectID+"/instances.json", new InstanceModel(data));
    }

    /**
     *
     * @param body
     * @return
     */
    public static Response sendCreateTestSet(String body)
    {
        return RequestFactory.doPost("/v2/projects/"+projectID+"/sets.json", body);
    }

    /**
     *
     * @return
     */
    public static Response sendGetTestSet()
    {
        return RequestFactory.doGet("/v2/projects/"+projectID+"sets.json");
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
        return RequestFactory.doPost("/v2/projects/"+projectID+"/sets.json", new SetsModel(data));
    }


}
