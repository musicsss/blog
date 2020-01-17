package com.xidian.blog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 米
 * @date 2020/1/16
 */
public class JsonUtils {
    public static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    /**
     * 对象转json字符串
     * @param value 对象
     * @return    json字符串
     * @throws Exception
     */
    public static String objectToJsonStr(Object value) throws Exception {
        return objectMapper.writeValueAsString(value);
    }

    /**
     * json字符串转对象
     * @param content  json字符串
     * @param valueType  对象类型
     * @return   对象
     * @throws Exception
     */
    public static <T> T objectFromJsonStr(String content,Class<T> valueType) throws Exception {
        T obj = objectMapper.readValue(content, valueType);
        return obj;
    }

}
