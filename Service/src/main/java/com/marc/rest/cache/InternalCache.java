package com.marc.rest.cache;

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

    public boolean isCached(String longitude, String latitude){
        return isCached(convertCordinatesToLocation(longitude, latitude));
    }

    public void save(Weather weather, String longitude, String latitude){
        save(weather, convertCordinatesToLocation(longitude, latitude));
    }

    public void save(Weather weather, String location){
        WeatherMeta meta = new WeatherMeta(weather);
        weatherMap.put(location, meta);
    }

    public Weather getWeather(String longitude, String latitude){
        return getWeather(convertCordinatesToLocation(longitude, latitude));
    }

    public Weather getWeather(String location){
        WeatherMeta result = weatherMap.get(location);
        if(result == null){
            return null;
        }
        return result.getWeather();
    }

    private String convertCordinatesToLocation(String longitude, String latitude){
        //TODO maybe Key as Class
        return longitude+","+latitude;
    }

}
