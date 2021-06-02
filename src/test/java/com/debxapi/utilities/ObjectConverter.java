package com.debxapi.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter {
    private  static ObjectMapper objectMapper = new ObjectMapper();
    public static <T> T convertJsonObjectToJavaObject(String jsonObject,Class clazz) throws JsonProcessingException {
        return (T)objectMapper.readValue(jsonObject,clazz);
    }
}
