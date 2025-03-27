import Utils.APIEndpoints;
import Utils.ReportSetup;
import Utils.ServiceHandler;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static Properties.TestProperties.REQRES_TestHost;
import static ServiceHelper.HeaderBuilder.getHeaders;
import static ServiceHelper.HeaderBuilder.getIOSHeaders;
import static ServiceHelper.RequestBodyBuilder.getCreateUserAPIRequest;


public class getListUsers extends ReportSetup {

    private ServiceHandler serviceHandler;

    public getListUsers(){
        serviceHandler = new ServiceHandler(REQRES_TestHost);
    }

    @Test
    public void verifyGetListUsersWithoutQueryParam() {
        //https://reqres.in/api/users
        JsonPath jsonPath = serviceHandler.executeGetAPI(APIEndpoints.getListUsersAPI,getHeaders(),new HashMap<>()).jsonPath();
        Assert.assertEquals(jsonPath.getInt("total"), 12);
        Assert.assertEquals(jsonPath.getInt("per_page"), 6);
    }

    @Test
    public void verifyGetListUsersWhenPageNumberIsSentInQueryParam() {
        //https://reqres.in/api/users?page=1
        Response response = serviceHandler.executeGetAPI(APIEndpoints.getListUsersAPI,getIOSHeaders(),new HashMap<>());
        ReportSetup.reportLog("============API Response===========");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(response.getStatusCode()>=200 && response.getStatusCode()<300,"response status code is not in 2XX series");
        if(!(response.getStatusCode()>=200 && response.getStatusCode()<300)){
            ReportSetup.reportLog(response.getBody().asString());
        }
        else {
            ReportSetup.reportLog("API response status code is 2XX series");
        }
        JsonPath jsonPath = response.jsonPath();
        softAssert.assertEquals(jsonPath.getInt("total"), 12);
        softAssert.assertEquals(jsonPath.getInt("per_page"), 6);
        softAssert.assertAll();
    }

    @Test
    public void verifyCreateUserFlow() {
        String name = "AG";
        String job = "Tester";
        Response response = serviceHandler.executePostAPI(APIEndpoints.getListUsersAPI,getIOSHeaders(),new HashMap<>(), getCreateUserAPIRequest(name,job));
        ReportSetup.reportLog("============API Response===========");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(response.getStatusCode()>=200 && response.getStatusCode()<300,"response status code is not in 2XX series");
        if(!(response.getStatusCode()>=200 && response.getStatusCode()<300)){
            ReportSetup.reportLog(response.getBody().asString());
        }
        else {
            ReportSetup.reportLog("API response status code is 2XX series");
        }
        softAssert.assertAll();
    }

}
