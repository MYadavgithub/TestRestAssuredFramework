package com.company.restframework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {


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
