import Utils.APIEndpoints;
import Utils.ReportSetup;
import Utils.ServiceHandler;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static Properties.TestProperties.REQRES_TestHost;
import static ServiceHelper.HeaderBuilder.getHeaders;
import static ServiceHelper.RequestBodyBuilder.getCreateUserAPIRequest;


public class getListUsers extends ReportSetup {

    private final ServiceHandler serviceHandler;

    public getListUsers(){
        serviceHandler = new ServiceHandler(REQRES_TestHost);
    }

    @Test
    public void verifyGetListUsersWhenPageNumberIsSentInQueryParam() {
        //https://reqres.in/api/users?page=1
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "1");
        Response response = serviceHandler.executeGetAPI(APIEndpoints.getListUsersAPI,getHeaders(),queryParams);
        Assert.assertTrue(response.getStatusCode()>=200 && response.getStatusCode()<300,"response status code is not in 2XX series");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getInt("total"), 12);
        Assert.assertEquals(jsonPath.getInt("per_page"), 6);
    }

    @Test
    public void verifyCreateUserFlow() {
        String name = "AG";
        String job = "Tester";
        Response response = serviceHandler.executePostAPI(APIEndpoints.getListUsersAPI,getHeaders(),new HashMap<>(), getCreateUserAPIRequest(name,job));
        Assert.assertTrue(response.getStatusCode()>=200 && response.getStatusCode()<300,"response status code is not in 2XX series");
    }

}
