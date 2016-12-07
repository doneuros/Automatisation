package com.marc.rest.cache;

import com.marc.rest.geo.OwnLatLng;
import com.marc.rest.internal.OwnerLocation;
import com.marc.rest.weather.Weather;
import net.aksingh.owmjapis.CurrentWeather;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marc on 13.11.16.
 */
public class InternalCache {
    private Map<String, WeatherMeta> weatherMap = new HashMap<>();

    public boolean isCached(String location){
        if(weatherMap.containsKey(location)){
            if(Days.daysBetween(weatherMap.get(location).getCreated(), new DateTime()).getDays()>=1){

                weatherMap.remove(location);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean isCached(OwnLatLng latLng){
        return isCached(latLng.getKey());
    }

    public void save(Weather weather, OwnLatLng latLng){
        save(weather, latLng.getKey());
    }

    public void save(Weather weather, String location){
        WeatherMeta meta = new WeatherMeta(weather);
        weatherMap.put(location, meta);
    }

    public Weather getWeather(OwnLatLng latLng){
        return getWeather(latLng.getKey());
    }

    public Weather getWeather(String location){
        WeatherMeta result = weatherMap.get(location);
        if(result == null){
            return null;
        }
        return result.getWeather();
    }



}
