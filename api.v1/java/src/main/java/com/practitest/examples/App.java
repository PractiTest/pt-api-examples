package com.practitest.examples;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class App {
    private static final String URI = "http://localhost:3000/api/automated_tests/upload_test_result.json";

    public static void main(String[] args) {
        if (args.length < 4) {
            printUsage();
            System.exit(1);
        }

        try {
            uploadTestResults(args[0], args[1], Integer.parseInt(args[2]), args[3],
                              Arrays.copyOfRange(args, 4, args.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printUsage() {
        System.err.println("java -cp <path-to-jar> com.practitest.examples.App <token> <testId> <exitCode> <result> [path-to-file0 path-to-file1 ...]");
    }

    private static void uploadTestResults(String token,
                                          String testId,
                                          int exitCode,
                                          String result,
                                          String[] paths) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(constructURI(token, testId, exitCode, result));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (String path : paths) {
                File f = new File(path);
                builder.addPart("result_files[" + f.getName() + "]", new FileBody(f));
            }
            httpPost.setEntity(builder.build());
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                System.out.println("------------");
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " +
                                       resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

    private static String constructURI(String token, String testId, int exitCode, String result) {
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(URI).append('?').append("api_token=").append(token).
            append("&instance_id=").append(testId).append("&exit_code=").append(exitCode).
            append("&result=").append(result);
        return uriBuilder.toString();
    }
}
