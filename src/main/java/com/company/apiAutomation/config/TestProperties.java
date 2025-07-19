package com.company.restframework.config;

public class TestProperties {
    private static PropertyReader propertyReader = new PropertyReader();

    public static final String REQRES_TestHost = propertyReader.getReqResHost();
}
