package com.practitest.api.example;

import com.jayway.restassured.response.Response;
import com.practitest.api.model.runs.StepModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PractiTestWriter {

    private static String getNameForNewRun() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String testSuite = System.getProperty("test");
        return testSuite + " " + dateFormat.format(date);
    }


    public static Integer createNewSet(List<Integer> testIDs)
    {
        Response response = PractiTestAPI.sendCreateTestSet(getNameForNewRun(), testIDs);
        return PractiTestAPI.sendCreateTestSet(getNameForNewRun(), testIDs).getBody().jsonPath().get("data.id") ;
    }

    public static Integer createNewInstance(Integer setID, Integer testID)
    {
        return PractiTestAPI.sendCreateInstance(setID, testID).getBody().jsonPath().get("data.id");
    }

    public static Integer submitResults(Integer instanceID, List<StepModel> stepModel)
    {
        return PractiTestAPI.sendCreateRun(instanceID, stepModel).getBody().jsonPath().get("get.id");

    }

}
