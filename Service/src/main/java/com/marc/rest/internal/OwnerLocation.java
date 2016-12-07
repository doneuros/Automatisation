package com.marc.rest.internal;

/**
 * Created by marc on 07.12.16.
 */
public class OwnerLocation {
    private double lon;
    private double lat;
    private String city;
    private static final OwnerLocation INSTANCE = new OwnerLocation();

    public static OwnerLocation getInstance() {
        return INSTANCE;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
