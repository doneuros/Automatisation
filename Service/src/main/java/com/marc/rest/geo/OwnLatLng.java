package com.marc.rest.geo;

import com.google.maps.model.LatLng;

/**
 * Created by marc on 07.12.16.
 */
public class OwnLatLng {
    private double lat;
    private double lng;

    public OwnLatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;

    }

    public double getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getKey() {
        return lat+","+lng;
    }

    public LatLng convertToGoogleLatLng(){
        return  new LatLng(lat, lng);
    }
}
