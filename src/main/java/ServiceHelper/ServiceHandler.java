package ServiceHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ServiceHandler {

    public static RequestSpecification requestspec;

    public ServiceHandler(String hostURL){
        requestspec = RestAssured.given().baseUri(hostURL);
    }

    public Response executeGetAPI(String endpoint, Map<String,String> headers, Map<String,String> queryParams){
        Response res = requestspec
                .headers(headers)
                .log().headers()
                .queryParams(queryParams)
                .log().parameters()
                .log().method()
                .log().uri()
                .get(endpoint);
        return res;
    }

    public Response executePostAPI(String endpoint, Map<String,String> headers, Map<String,String> queryParams, String body){
        Response res = requestspec
                .headers(headers)
                .log().headers()
                .queryParams(queryParams)
                .log().parameters()
                .body(body)
                .log().body()
                .log().method()
                .log().uri()
                .post(endpoint);
        return res;
    }


}
