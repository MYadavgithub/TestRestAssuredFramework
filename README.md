# TestRestAssuredFramework

## Overview
TestRestAssuredFramework is a modular, data-driven API automation framework built with Java, TestNG, and RestAssured. It is designed for robust, maintainable, and scalable API testing, supporting configuration management, data-driven testing via Excel, and comprehensive HTML reporting.

## Features
- **Data-driven testing** using Excel files
- **Centralized configuration** for easy environment management
- **Reusable helpers** for headers, request bodies, and endpoints
- **POJO models** for request/response payloads
- **Comprehensive reporting** with HTML output
- **Extensible structure** for adding new tests and endpoints

## Project Structure
```
TestRestAssuredFramework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/company/apiAutomation/
│   │   │       ├── config/         # Configuration readers
│   │   │       ├── endpoints/      # API endpoint definitions
│   │   │       ├── helpers/        # Request/response helpers
│   │   │       ├── models/         # POJOs for request/response
│   │   │       └── utils/          # Common utilities
│   │   └── resources/              # Properties files, etc.
│   └── test/
│       ├── java/                   # Test classes
│       └── resources/              # Test data (Excel, XML)
├── report/                         # Generated test reports
├── docs/                           # Project documentation
├── build.gradle, settings.gradle   # Gradle build files
└── README.md                       # Project overview
```

## Getting Started
1. **Clone the repository**
2. **Install dependencies** using Gradle:
   ```sh
   ./gradlew build
   ```
3. **Configure properties** in `src/main/resources/Properties.properties`
4. **Add test data** in `src/test/resources/TestData.xlsx`
5. **Run tests** with TestNG or your IDE
6. **View reports** in the `report/` directory

## Documentation
- For a detailed technical flow, see the [Architecture Sequence Diagram](docs/architecture.md).

---

*This framework is designed for easy extension—add new endpoints, test data, or test classes as your API evolves.* 