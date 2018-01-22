package com.practitest.api.example;


import com.practitest.api.common.RequestFactory;
import com.practitest.api.model.instance.InstanceModel;
import com.practitest.api.model.runs.*;
import com.practitest.api.model.sets.Instances;
import com.practitest.api.model.sets.SetsModel;
import com.jayway.restassured.response.Response;

import java.util.List;

public class PractiTestAPI {

    private final static  String projectID = System.getProperty("PROJECT_ID");

    public static Response sendGetSteps(String id)
    {
        return RequestFactory.doGet("com/v2/projects/"+projectID+"/steps.json?test-ids=" +id);
    }

    public static void sendCreateRun(String body)
    {
        RequestFactory.doPost("com/v2/projects/"+projectID+"/runs.json", body);
    }

    public static Response sendCreateRun(int instanceID, List<StepModel> step)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID));
        data.setSteps(new Steps(step));
        return RequestFactory.doPost("com/v2/projects/"+projectID+"/runs.json", new RunsModel(data)).getBody().jsonPath().get("data.id");
    }

    public static Response sendCreateRun(int instanceID)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID));
        return RequestFactory.doPost("com/v2/projects/"+projectID+"/runs.json", new RunsModel(data)).getBody().jsonPath().get("data.id");
    }

    public static void sendCreateInstance(String body)
    {
        RequestFactory.doPost("com/v2/projects/"+projectID+"/instances.json", body).prettyPrint();
    }

    public static Response sendCreateInstance(int setID, int testID)
    {
        com.practitest.api.model.instance.Data data = new com.practitest.api.model.instance.Data();
        data.setAttributes(new com.practitest.api.model.instance.Attributes(setID, testID));

        return RequestFactory.doPost("com/v2/projects/"+projectID+"/instances.json", new InstanceModel(data));
    }

    public static Response sendCreateTestSet(String body)
    {
        return RequestFactory.doPost("com/v2/projects/"+projectID+"/sets.json", body);
    }

    public static Response sendCreateTestSet(String name, List<Integer> testIDs)
    {
        com.practitest.api.model.sets.Data data = new com.practitest.api.model.sets.Data();
        data.setInstances(new Instances(testIDs));
        data.setAttributes(new com.practitest.api.model.sets.Attributes(name));
        return RequestFactory.doPost("com/v2/projects/"+projectID+"/sets.json", new SetsModel(data));
    }


}
