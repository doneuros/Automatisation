package com.marc.rest.json;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marc on 20.10.16.
 */
public class AbstractJSONClass {

    private Map<String, Object> otherProperties = new HashMap<String, Object>();

    @JsonAnySetter
    public void set(String name, Object value) {
        otherProperties.put(name, value);
    }

    @JsonAnyGetter
    public Map<String,Object> any() {
        return otherProperties;
    }
}
