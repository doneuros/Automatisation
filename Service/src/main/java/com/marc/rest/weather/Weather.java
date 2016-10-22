package com.marc.rest.weather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marc.rest.json.AbstractJSONClass;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Created by marc on 20.10.16.
 */
@XmlRootElement
public class Weather extends AbstractJSONClass {


    private double temprature;

    private String description;

    public Weather() {
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
