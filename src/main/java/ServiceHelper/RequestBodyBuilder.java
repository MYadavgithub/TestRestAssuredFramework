package ServiceHelper;

import ServiceHelper.PojoPayloads.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestBodyBuilder {

    public static String getCreateUserAPIRequest(String name, String job){
        CreateUserRequest createUserRequestObj=  CreateUserRequest.builder().name(name).job(job).build();
        return returnJsonString(createUserRequestObj);
    }

    public static String returnJsonString(Object object){
        String jsonString = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
