package com.practitest.api.example;


import com.practitest.api.common.RequestFactory;
import com.practitest.api.model.instance.InstanceModel;
import com.practitest.api.model.runs.*;
import com.practitest.api.model.sets.Instances;
import com.practitest.api.model.sets.SetsModel;
import com.jayway.restassured.response.Response;

import java.util.List;

public class PractiTestAPI {

    public static Response sendGetSteps(String id)
    {
        return RequestFactory.doGet("com/v2/projects/4650/steps.json?test-ids=" +id);
    }

    public static void sendCreateRun(String body)
    {
        RequestFactory.doPost("com/v2/projects/4650/runs.json", body).prettyPrint();
    }

    public static void sendCreateRun(int instanceID, List<StepModel> step)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID));
        data.setSteps(new Steps(step));
        RequestFactory.doPost("com/v2/projects/4650/runs.json", new RunsModel(data)).prettyPrint();
    }

    public static void sendCreateInstance(String body)
    {
        RequestFactory.doPost("com/v2/projects/4650/instances.json", body).prettyPrint();
    }

    public static void sendCreateInstance(int setID, int testID)
    {
        com.practitest.api.model.instance.Data data = new com.practitest.api.model.instance.Data();
        data.setAttributes(new com.practitest.api.model.instance.Attributes(setID, testID));

        RequestFactory.doPost("com/v2/projects/4650/instances.json", new InstanceModel(data)).prettyPrint();
    }

    public static void sendCreateTestSet(String body)
    {
        RequestFactory.doPost("com/v2/projects/4650/sets.json", body).prettyPrint();
    }

    public static void sendCreateTestSet(String name, List<Integer> testIDs)
    {
        com.practitest.api.model.sets.Data data = new com.practitest.api.model.sets.Data();
        data.setInstances(new Instances(testIDs));
        data.setAttributes(new com.practitest.api.model.sets.Attributes(name));
        RequestFactory.doPost("com/v2/projects/4650/sets.json", new SetsModel(data)).prettyPrint();
    }


}
