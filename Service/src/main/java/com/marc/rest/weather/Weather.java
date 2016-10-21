package com.marc.rest.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marc.rest.json.AbstractJSONClass;

/**
 * Created by marc on 20.10.16.
 */
public class Weather extends AbstractJSONClass {

    @JsonProperty("description")
    private String description;

    public Weather() {
    }

    public String getDescription() {
        return description;
    }
}
