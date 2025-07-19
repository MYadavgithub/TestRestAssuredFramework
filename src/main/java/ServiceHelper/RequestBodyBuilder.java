package ServiceHelper;

import ServiceHelper.PojoPayloads.CreateUserRequest;
import Utils.CommonUtils;

public class RequestBodyBuilder {

    public static String getCreateUserAPIRequest(String name, String job){
        CreateUserRequest createUserRequestObj=  CreateUserRequest.builder().name(name).job(job).build();
        return CommonUtils.returnJsonString(createUserRequestObj);
    }

}
