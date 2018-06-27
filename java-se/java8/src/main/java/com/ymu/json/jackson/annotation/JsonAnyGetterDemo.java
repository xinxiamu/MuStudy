package com.ymu.json.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * 注解@JsonAnyGetter使用。
 */
public class JsonAnyGetterDemo {

    private String name;
    private Map<String, String> properties;

    public JsonAnyGetterDemo(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    public static void main(String[] args) throws JsonProcessingException {
        JsonAnyGetterDemo bean = new JsonAnyGetterDemo("zmt");
        String result = new ObjectMapper().writeValueAsString(bean);
        System.out.println(">>result:" + result);
    }
}
