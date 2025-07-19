package com.company.restframework.helpers;

import com.company.restframework.models.CreateUserRequest;
import com.company.restframework.utils.CommonUtils;

public class RequestBodyBuilder {

    public static String getCreateUserAPIRequest(String name, String job){
        CreateUserRequest createUserRequestObj=  CreateUserRequest.builder().name(name).job(job).build();
        return CommonUtils.returnJsonString(createUserRequestObj);
    }

}
