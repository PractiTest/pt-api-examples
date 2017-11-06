package api.practitest;


import api.common.RequestFactory;
import api.practitest.instance.InstanceModel;
import api.practitest.runs.*;
import api.practitest.sets.Instances;
import api.practitest.sets.SetsModel;
import com.jayway.restassured.response.Response;

import java.util.List;

public class PractiTestAPI {

    public static Response sendGetSteps(String id)
    {
        return RequestFactory.doGet("api/v2/projects/4650/steps.json?test-ids="+id);
    }

    public static void sendCreateRun(String body)
    {
        RequestFactory.doPost("api/v2/projects/4650/runs.json", body).prettyPrint();
    }

    public static void sendCreateRun(int instanceID, List<StepModel> step)
    {
        Data data = new Data();
        data.setType("instances");
        data.setAttributes(new Attributes(instanceID));
        data.setSteps(new Steps(step));
        RequestFactory.doPost("api/v2/projects/4650/runs.json", new RunsModel(data)).prettyPrint();
    }

    public static void sendCreateInstance(String body)
    {
        RequestFactory.doPost("api/v2/projects/4650/instances.json", body).prettyPrint();
    }

    public static void sendCreateInstance(int setID, int testID)
    {
        api.practitest.instance.Data data = new api.practitest.instance.Data();
        data.setAttributes(new api.practitest.instance.Attributes(setID, testID));

        RequestFactory.doPost("api/v2/projects/4650/instances.json", new InstanceModel(data)).prettyPrint();
    }

    public static void sendCreateTestSet(String body)
    {
        RequestFactory.doPost("api/v2/projects/4650/sets.json", body).prettyPrint();
    }

    public static void sendCreateTestSet(String name, List<Integer> testIDs)
    {
        api.practitest.sets.Data data = new api.practitest.sets.Data();
        data.setInstances(new Instances(testIDs));
        data.setAttributes(new api.practitest.sets.Attributes(name));
        RequestFactory.doPost("api/v2/projects/4650/sets.json", new SetsModel(data)).prettyPrint();
    }


}
