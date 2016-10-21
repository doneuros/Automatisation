package com.marc.rest.weather;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.marc.rest.json.AbstractJSONClass;
/**
 * Created by marc on 20.10.16.
 */
public class WeatherJSON extends AbstractJSONClass{
    @JsonProperty("weather")
    public Weather weahter;

    @JsonProperty("main")
    public MainWeatherProperties main;

    public WeatherJSON() {
    }




}


