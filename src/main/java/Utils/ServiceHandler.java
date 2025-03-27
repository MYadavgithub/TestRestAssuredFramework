package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;

import java.util.Map;

public class ServiceHandler {

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

        // ✅ Only log full details if the API request fails
        if (statusCode >= 200 && statusCode < 300 && responseBody != null && !responseBody.isEmpty()) {
            Reporter.log("<b>Executed " + method + " API:</b> " + endpoint, true);
            return;
        }

        // 🔴 If there's an error, log all API details
        Reporter.log("<font color='red'><b>API FAILED:</b> " + method + " " + endpoint + "</font>", true);
        Reporter.log("<b>Status Code:</b> " + statusCode, true);
        Reporter.log("<b>Headers:</b> " + headers, true);
        Reporter.log("<b>Query Params:</b> " + queryParams, true);
        
        if (requestBody != null) {
            Reporter.log("<b>Request Body:</b> " + requestBody, true);
        }

        if (responseBody == null || responseBody.isEmpty()) {
            Reporter.log("<font color='red'><b>Response body is NULL or EMPTY!</b></font>", true);
        } else {
            Reporter.log("<b>Response Body:</b> " + responseBody, true);
        }
    }
}
