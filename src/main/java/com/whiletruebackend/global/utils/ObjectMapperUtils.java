package com.whiletruebackend.global.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ObjectMapperUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String mapToString(Map<String, Object> mapJson) {
        String strJson;

        try {
            strJson = objectMapper.writeValueAsString(mapJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return strJson;
    }
}
