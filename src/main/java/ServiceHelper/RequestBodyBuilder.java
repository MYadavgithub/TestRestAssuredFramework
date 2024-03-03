package ServiceHelper;

import ServiceHelper.PojoPayloads.CreateUserRequest;
import Utils.GenericBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestBodyBuilder {

    public static String getCreateUserAPIRequest(String name, String job){
        CreateUserRequest createUserRequestObj=  GenericBuilder.build(CreateUserRequest.class)
        .with(s->s.setName(name))
                .with(s->s.setJob(job)).get();
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
