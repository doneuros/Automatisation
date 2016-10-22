package com.marc.rest.weather;

import com.marc.rest.json.AbstractJSONClass;

/**
 * Created by marc on 20.10.16.
 */
public class Weather extends AbstractJSONClass {


    private double temprature;

    private String description;

    public Weather() {
    }

    public double getTempratureInCalcius(){
        return temprature-273.15;
    }

    public double getTemprature() {
        return temprature;
    }

    public void setTemprature(double temprature) {
        this.temprature = temprature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
