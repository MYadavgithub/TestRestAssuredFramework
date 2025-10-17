package com.company.apiAutomation.helpers;

import com.company.apiAutomation.models.CreateUserRequest;
import com.company.apiAutomation.utils.CommonUtils;

public class RequestBodyBuilder {

    public static String getCreateUserAPIRequest(String name, String job){
        CreateUserRequest createUserRequestObj=  CreateUserRequest.builder().name(name).job(job).build();
        return CommonUtils.returnJsonString(createUserRequestObj);
    }

}
