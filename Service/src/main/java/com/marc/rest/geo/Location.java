package com.marc.rest.geo;

/**
 * Created by marc on 16.11.16.
 */
public class Location {
    private String City = "";
    private double longitude;
    private double latitude;

    public Location(String city, double longitude, double latitude) {
        City = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location() {

    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
