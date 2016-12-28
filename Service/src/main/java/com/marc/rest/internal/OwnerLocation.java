package com.marc.rest.internal;

import com.marc.rest.geo.OwnLatLng;

/**
 * Created by marc on 07.12.16.
 */
public class OwnerLocation {
    //TODO change to own location
    private OwnLatLng latLon = new OwnLatLng(49.0068901,8.403652700000066);
    private String city;
    private static final OwnerLocation INSTANCE = new OwnerLocation();

    public static OwnerLocation getInstance() {
        return INSTANCE;
    }

    public OwnLatLng getLatLon() {
        return latLon;
    }

    public void setLatLon(OwnLatLng latLon) {
        this.latLon = latLon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
