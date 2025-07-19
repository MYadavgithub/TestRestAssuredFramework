# Framework Architecture

## Sequence Diagram

```mermaid
sequenceDiagram
    participant TestNG as TestNG (testng.xml)
    participant TestClass as Test Class (getListUsers.java)
    participant Config as Config Loader (PropertyReader/TestProperties)
    participant DataProvider as TestDataProvider (Excel)
    participant HeaderBuilder as HeaderBuilder
    participant RequestBodyBuilder as RequestBodyBuilder
    participant Model as Model/POJO (CreateUserRequest)
    participant Utils as CommonUtils
    participant ServiceHandler as ServiceHandler
    participant Endpoints as APIEndpoints
    participant API as External API Server
    participant Report as ReportSetup/SuiteListener

    TestNG->>TestClass: Run test method
    TestClass->>Config: Load config (base URL, etc.)
    TestClass->>DataProvider: Request test data
    DataProvider->>DataProvider: Read from TestData.xlsx
    DataProvider-->>TestClass: Return test data
    TestClass->>HeaderBuilder: Build headers
    TestClass->>RequestBodyBuilder: Build request body
    RequestBodyBuilder->>Model: Use POJO for request structure
    TestClass->>Utils: Use utility methods (if needed)
    TestClass->>ServiceHandler: Call API (pass headers, body, config)
    ServiceHandler->>Endpoints: Get endpoint path
    ServiceHandler->>API: Send HTTP request
    API-->>ServiceHandler: Return HTTP response
    ServiceHandler-->>TestClass: Return response
    TestClass->>Report: Log result, update report
```

## Component Explanations

- **TestNG (testng.xml):** Entry point for the test suite, defines which tests to run and configuration.
- **Test Class:** Contains test methods, uses `@Test` and `@DataProvider` annotations.
- **Config Loader:** Loads environment and endpoint details before the test.
- **TestDataProvider:** Reads test data from Excel using Apache POI, provides it to test methods.
- **HeaderBuilder/RequestBodyBuilder:** Helper classes to construct HTTP headers and request bodies.
- **Model/POJO:** Represents the structure of request payloads.
- **CommonUtils:** General-purpose utility methods used in the test flow.
- **ServiceHandler:** Centralizes the logic for making API calls, using endpoint info from `APIEndpoints`.
- **APIEndpoints:** Centralized location for all endpoint paths (URLs).
- **External API Server:** The actual API under test.
- **ReportSetup/SuiteListener:** Handles logging, reporting, and hooks for test events. 