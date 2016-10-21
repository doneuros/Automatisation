package com.marc.rest.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marc.rest.json.AbstractJSONClass;

/**
 * Created by marc on 20.10.16.
 */
public class MainWeatherProperties extends AbstractJSONClass {

    @JsonProperty("temp")
    private double temp;

    public MainWeatherProperties() {
    }

    public double getTemp() {
        return temp;
    }
}
