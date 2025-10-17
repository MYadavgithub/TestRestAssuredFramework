# Quick Reference Guide - REST Assured Framework

## 🚀 Quick Start

### **1. Setup Project**
```bash
# Clone and build
git clone <repository-url>
cd TestRestAssuredFramework
./gradlew build
```

### **2. Run Tests**
```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests getListUsers

# Run with TestNG XML
java -cp "lib/*" org.testng.TestNG src/test/resources/testng.xml
```

### **3. View Reports**
```bash
# Open HTML report
open report/APIAutomationReport.html
```

---

## 📝 Creating New Tests

### **1. Basic Test Structure**
```java
import Utils.ReportSetup;
import Utils.ServiceHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyNewTest extends ReportSetup {
    
    private final ServiceHandler serviceHandler;
    
    public MyNewTest() {
        serviceHandler = new ServiceHandler(REQRES_TestHost);
    }
    
    @Test
    public void myTestMethod() {
        // Test implementation
    }
}
```

### **2. GET API Test**
```java
@Test
public void testGetAPI() {
    HashMap<String, String> queryParams = new HashMap<>();
    queryParams.put("page", "1");
    
    Response response = serviceHandler.executeGetAPI(
        APIEndpoints.getListUsersAPI,
        getHeaders(),
        queryParams
    );
    
    Assert.assertTrue(response.getStatusCode() >= 200 && 
                     response.getStatusCode() < 300);
}
```

### **3. POST API Test**
```java
@Test
public void testPostAPI() {
    String requestBody = getCreateUserAPIRequest("John", "Developer");
    
    Response response = serviceHandler.executePostAPI(
        APIEndpoints.getListUsersAPI,
        getHeaders(),
        new HashMap<>(),
        requestBody
    );
    
    Assert.assertTrue(response.getStatusCode() >= 200 && 
                     response.getStatusCode() < 300);
}
```

### **4. Data-Driven Test**
```java
@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class)
public void testWithData(String name, String job) {
    String requestBody = getCreateUserAPIRequest(name, job);
    
    Response response = serviceHandler.executePostAPI(
        APIEndpoints.getListUsersAPI,
        getHeaders(),
        new HashMap<>(),
        requestBody
    );
    
    Assert.assertTrue(response.getStatusCode() >= 200 && 
                     response.getStatusCode() < 300);
}
```

---

## 🔧 Configuration

### **1. Add New Endpoint**
```java
// In APIEndpoints.java
public interface APIEndpoints {
    String getListUsersAPI = "/api/users";
    String createUserAPI = "/api/users";  // Add new endpoint
    String updateUserAPI = "/api/users/{id}";  // Add new endpoint
}
```

### **2. Add New Environment**
```properties
# In Properties.properties
req_res_service_testHost=https://reqres.in/
staging_host=https://staging.reqres.in/
production_host=https://api.reqres.in/
```

### **3. Add New Headers**
```java
// In HeaderBuilder.java
public static Map<String,String> getHeaders() {
    Map<String,String> headers = new HashMap<>();
    headers.put("content-Type","application/json");
    headers.put("x-consumer-name","AG");
    headers.put("Authorization","Bearer token");  // Add new header
    return headers;
}
```

---

## 📊 Test Data Management

### **1. Excel Structure**
```excel
# TestData.xlsx
| Name | Job       | ExpectedStatus |
|------|-----------|----------------|
| John | Developer | 201           |
| Jane | Tester    | 201           |
| Bob  | Manager   | 201           |
```

### **2. Reading Test Data**
```java
@DataProvider(name = "TestData")
public static Object[][] getTestDatafromExcel() {
    // Automatically reads from TestData.xlsx
    // Returns Object[][] array
}
```

### **3. Using Test Data**
```java
@Test(dataProvider = "TestData")
public void testWithExcelData(String name, String job, int expectedStatus) {
    // Test implementation using Excel data
}
```

---

## 🎨 Reporting

### **1. Custom Logging**
```java
// Log custom messages
reportLog("Starting API test");
reportLog("Request sent successfully");
reportLog("Response received: " + response.getBody().asString());
```

### **2. Assertion Logging**
```java
@Test
public void testWithAssertions() {
    Response response = serviceHandler.executeGetAPI(
        APIEndpoints.getListUsersAPI,
        getHeaders(),
        new HashMap<>()
    );
    
    // Assertions are automatically logged
    Assert.assertEquals(response.getStatusCode(), 200);
    Assert.assertNotNull(response.getBody());
}
```

---

## 🔍 Debugging

### **1. Enable Detailed Logging**
```java
// In ServiceHandler.java
private void logAPIResponse(String method, String endpoint, 
                          Map<String, String> headers, 
                          Map<String, String> queryParams, 
                          String requestBody, Response response) {
    // Detailed logging for failed requests
    if (response.getStatusCode() >= 400) {
        reportLog("API FAILED: " + method + " " + endpoint);
        reportLog("Status Code: " + response.getStatusCode());
        reportLog("Response Body: " + response.getBody().asString());
    }
}
```

### **2. Check Reports**
- **Location**: `report/APIAutomationReport.html`
- **Features**: 
  - Test status (Pass/Fail)
  - Request/response details
  - Error messages
  - Execution time

---

## 📁 File Structure Reference

```
TestRestAssuredFramework/
├── src/
│   ├── main/java/
│   │   ├── com/company/apiAutomation/
│   │   │   ├── config/PropertyReader.java      # Configuration
│   │   │   ├── endpoints/APIEndpoints.java     # Endpoints
│   │   │   ├── helpers/HeaderBuilder.java      # Headers
│   │   │   ├── models/CreateUserRequest.java   # POJOs
│   │   │   └── utils/CommonUtils.java          # Utilities
│   │   └── Utils/
│   │       ├── ServiceHandler.java             # API execution
│   │       ├── ReportSetup.java                # Reporting
│   │       ├── TestDataProvider.java           # Data providers
│   │       └── SuiteListener.java              # Test listeners
│   └── test/
│       ├── java/getListUsers.java              # Test classes
│       └── resources/
│           ├── TestData.xlsx                    # Test data
│           └── testng.xml                       # Test configuration
├── report/APIAutomationReport.html             # Generated reports
└── build.gradle                                # Build configuration
```

---

## ⚡ Common Patterns

### **1. Response Validation**
```java
@Test
public void validateResponse() {
    Response response = serviceHandler.executeGetAPI(
        APIEndpoints.getListUsersAPI,
        getHeaders(),
        new HashMap<>()
    );
    
    // Status code validation
    Assert.assertEquals(response.getStatusCode(), 200);
    
    // JSON path validation
    JsonPath jsonPath = response.jsonPath();
    Assert.assertEquals(jsonPath.getInt("total"), 12);
    Assert.assertEquals(jsonPath.getInt("per_page"), 6);
    
    // Response time validation
    Assert.assertTrue(response.getTime() < 5000); // 5 seconds
}
```

### **2. Error Handling**
```java
@Test
public void handleErrors() {
    try {
        Response response = serviceHandler.executeGetAPI(
            APIEndpoints.getListUsersAPI,
            getHeaders(),
            new HashMap<>()
        );
        
        // Success case
        Assert.assertTrue(response.getStatusCode() >= 200 && 
                         response.getStatusCode() < 300);
    } catch (Exception e) {
        // Error case
        reportLog("Test failed with error: " + e.getMessage());
        throw e;
    }
}
```

### **3. Data Provider with Multiple Columns**
```java
@DataProvider(name = "UserData")
public static Object[][] getUserData() {
    return new Object[][] {
        {"John", "Developer", 201},
        {"Jane", "Tester", 201},
        {"Bob", "Manager", 201}
    };
}

@Test(dataProvider = "UserData")
public void testUserCreation(String name, String job, int expectedStatus) {
    // Test implementation
}
```

---

## 🔧 Troubleshooting

### **1. Build Issues**
```bash
# Clean and rebuild
./gradlew clean build

# Check dependencies
./gradlew dependencies
```

### **2. Test Execution Issues**
```bash
# Run with debug
./gradlew test --debug

# Check TestNG XML
cat src/test/resources/testng.xml
```

### **3. Data Provider Issues**
```bash
# Check Excel file
ls -la src/test/resources/TestData.xlsx

# Verify file format
file src/test/resources/TestData.xlsx
```

---

## 📚 Best Practices

### **1. Test Organization**
- Keep test methods focused and single-purpose
- Use descriptive test method names
- Group related tests in the same class

### **2. Data Management**
- Use Excel for complex test data
- Keep test data separate from test logic
- Use data providers for multiple scenarios

### **3. Assertions**
- Validate both status codes and response content
- Use specific assertions rather than generic ones
- Log important validation steps

### **4. Reporting**
- Add meaningful log messages
- Use custom assertions for better reporting
- Include relevant context in error messages

---

## 🎯 Framework Extensions

### **1. Add New HTTP Methods**
```java
// In ServiceHandler.java
public Response executePutAPI(String endpoint, Map<String, String> headers, 
                            Map<String, String> queryParams, String body) {
    Response response = requestSpec
        .headers(headers)
        .queryParams(queryParams)
        .body(body)
        .put(endpoint);
    
    logAPIResponse("PUT", endpoint, headers, queryParams, body, response);
    return response;
}
```

### **2. Add New Data Sources**
```java
// In TestDataProvider.java
@DataProvider(name = "JSONData")
public static Object[][] getTestDataFromJSON() {
    // JSON file reading logic
    return new Object[][] {
        // Test data from JSON
    };
}
```

### **3. Add Custom Listeners**
```java
// Create new listener
public class CustomListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        // Custom success handling
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        // Custom failure handling
    }
}
```

---

*This quick reference guide provides essential information for developers to quickly understand and use the REST Assured framework effectively.* 