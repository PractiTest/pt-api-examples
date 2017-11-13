package com.practitest.api.common;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.util.Properties;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.SSLConfig.sslConfig;


public class RequestFactory {

    private static Properties props = new Properties();

    private static void loadProperties()
    {
        try{
            props.load(new FileInputStream("src/main/resources/project.properties"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static byte[] encoding(){
        loadProperties();
        return Base64.encodeBase64((props.getProperty("DEVELOPER_EMAIL") + ":" + props.getProperty("API_TOKEN")).getBytes());
    }

    public static Response doGet(String apiEndPoint)
    {
        loadProperties();
        System.out.println("================================================================================");
        System.out.println("GET  "+props.getProperty("URI")+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .get(props.getProperty("URI")+apiEndPoint)
                .then().extract().response();
    }

        public static Response doPost(String apiEndPoint, String body)
    {
        loadProperties();
        System.out.println("================================================================================");
        System.out.println("POST  "+props.getProperty("URI")+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .body(body)
                .post(props.getProperty("URI")+apiEndPoint)
                .then().extract().response();
    }

    public static Response doPost(String apiEndPoint, Object body)
    {
        loadProperties();
        System.out.println("================================================================================");
        System.out.println("POST  "+props.getProperty("URI")+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .body(body)
                .post(props.getProperty("URI")+apiEndPoint)
                .then().extract().response();
    }

        public static Response doPut(String apiEndPoint, String body)
    {
        loadProperties();
        System.out.println("================================================================================");
        System.out.println("PUT  "+props.getProperty("URI")+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .body(body)
                .put(props.getProperty("URI")+apiEndPoint)
                .then().extract().response();
    }

        public static Response doDelete(String apiEndPoint, String body)
    {
        loadProperties();
        System.out.println("================================================================================");
        System.out.println("DELETE  "+props.getProperty("URI")+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .body(body)
                .delete(props.getProperty("URI")+apiEndPoint)
                .then().extract().response();
    }
}
