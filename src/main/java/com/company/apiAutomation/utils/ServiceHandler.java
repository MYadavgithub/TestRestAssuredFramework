package com.company.apiAutomation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ServiceHandler extends ReportSetup {

    private final RequestSpecification requestSpec;

    public ServiceHandler(String hostURL) {
        this.requestSpec = RestAssured.given().baseUri(hostURL);
    }

    public Response executeGetAPI(String endpoint, Map<String, String> headers, Map<String, String> queryParams) {
        Response response = requestSpec
                .headers(headers)
                .queryParams(queryParams)
                .get(endpoint);

        logAPIResponse("GET", endpoint, headers, queryParams, null, response);
        return response;
    }

    public Response executePostAPI(String endpoint, Map<String, String> headers, Map<String, String> queryParams, String body) {
        Response response = requestSpec
                .headers(headers)
                .queryParams(queryParams)
                .body(body)
                .post(endpoint);

        logAPIResponse("POST", endpoint, headers, queryParams, body, response);
        return response;
    }

    private void logAPIResponse(String method, String endpoint, Map<String, String> headers, Map<String, String> queryParams, String requestBody, Response response) {
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        // Log summary for successful requests
        if (statusCode >= 200 && statusCode < 300 && responseBody != null && !responseBody.isEmpty()) {
            reportLog("Executed " + method + " API: " + endpoint);
            return;
        }

        // Log full details for failed requests
        reportLog("API FAILED: " + method + " " + endpoint);
        reportLog("Status Code: " + statusCode);
        reportLog("Headers: " + headers);
        reportLog("Query Params: " + queryParams);

        if (requestBody != null) {
            reportLog("Request Body: " + requestBody);
        }
        if (responseBody == null || responseBody.isEmpty()) {
            reportLog("Response body is NULL or EMPTY!");
        } else {
            reportLog("Response Body: " + responseBody);
        }
    }
}
