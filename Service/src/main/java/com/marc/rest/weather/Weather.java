package com.marc.rest.weather;

/**
 * Created by marc on 07.12.16.
 */
public class Weather {

    private boolean hasRain;
    private float temprature;

    public Weather(boolean hasRain, float temprature) {
        this.hasRain = hasRain;
        this.temprature = temprature;
    }

    public boolean isHasRain() {
        return hasRain;
    }

    public void setHasRain(boolean hasRain) {
        this.hasRain = hasRain;
    }

    public float getTemprature() {
        return temprature;
    }

    public void setTemprature(float temprature) {
        this.temprature = temprature;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "Regnerisch=" + hasRain +
                ", temprature=" + temprature +
                '}';
    }
}
