package com.practitest.api.common;

import com.Config.GeneralConfig;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.SSLConfig.sslConfig;


public class RequestFactory {

    private static String URI = GeneralConfig.getConfigurationValue(GeneralConfig.URI);

    private static byte[] encoding(){
        return Base64.encodeBase64((GeneralConfig.getConfigurationValue(GeneralConfig.DEV_MAIL) + ":" + GeneralConfig.getConfigurationValue(GeneralConfig.API_TOKEN)).getBytes());
    }

    public static Response doGet(String apiEndPoint)
    {
        System.out.println("================================================================================");
        System.out.println("GET  "+ URI+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .get(URI+apiEndPoint)
                .then().extract().response();
    }

        public static Response doPost(String apiEndPoint, String body)
    {
        System.out.println("================================================================================");
        System.out.println("POST  "+URI+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .body(body)
                .post(URI+apiEndPoint)
                .then().extract().response();
    }

    public static Response doPost(String apiEndPoint, Object body)
    {
        System.out.println("================================================================================");
        System.out.println("POST  "+URI+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .body(body)
                .post(URI+apiEndPoint)
                .then().extract().response();
    }

        public static Response doPut(String apiEndPoint, String body)
    {
        System.out.println("================================================================================");
        System.out.println("PUT  "+URI+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .body(body)
                .put(URI+apiEndPoint)
                .then().extract().response();
    }

        public static Response doDelete(String apiEndPoint, String body)
    {
        System.out.println("================================================================================");
        System.out.println("DELETE  "+URI+apiEndPoint);
        System.out.println("================================================================================");
        return given()
                .log().headers()
                .log().body()
                .config(RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + new String(encoding()))
                .body(body)
                .delete(URI+apiEndPoint)
                .then().extract().response();
    }
}
