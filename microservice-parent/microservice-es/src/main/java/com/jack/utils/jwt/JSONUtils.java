package com.jack.utils.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author ：liyongjie
 * @ClassName ：JSONUtils
 * @date ： 2019-11-27 15:20
 * @modified By：
 */
public class JSONUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public JSONUtils() {
    }

    public static String toJSON(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static Object convert(String json, Class clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    public static Map convertMap(String json) throws IOException {
        return (Map)convert(json, Map.class);
    }

    public static List convertList(String json) throws IOException {
        return (List)convert(json, List.class);
    }
}
