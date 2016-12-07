package com.marc.rest.geo;

/**
 * Created by marc on 07.12.16.
 */
public class OwnLatLng {
    private float lat;
    private float lng;

    public OwnLatLng(float lat, float lng) {
        this.lat = lat;
        this.lat = lng;

    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getKey() {
        return lat+","+lng;
    }
}
