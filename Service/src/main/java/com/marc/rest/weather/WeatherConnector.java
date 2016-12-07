package com.marc.rest.weather;

import com.marc.rest.geo.OwnLatLng;
import com.marc.rest.sensitive.SensitiveKeys;
import org.apache.log4j.Logger;


import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;
import java.net.ConnectException;

/**
 * Created by marc on 13.11.16.
 */
public class WeatherConnector {

    final static Logger logger = Logger.getLogger(WeatherConnector.class);
    private OpenWeatherMap owm;

    public WeatherConnector(){
        owm = new OpenWeatherMap(SensitiveKeys.owmKey);
    }

    public void setOpenWeatherMap(OpenWeatherMap owm){
        this.owm = owm;
    }

    public Weather getWeather(OwnLatLng latLng) throws IOException {
        CurrentWeather currentWeather = owm.currentWeatherByCoordinates(latLng.getLat(),latLng.getLng());
        return getWeatherInternal(currentWeather);
    }

    public Weather getWeather(String location) throws IOException {
        CurrentWeather currentWeather = owm.currentWeatherByCityName(location);
        return getWeatherInternal(currentWeather);
    }

    private Weather getWeatherInternal(CurrentWeather currentWeather) throws IOException {
        if(!currentWeather.isValid()){
            throw new IOException("Weather Info are not falid");
        }
        Weather weather = new Weather();
        if(currentWeather.getRainInstance()!=null){
            weather.setHasRain(false);
        }
        if(currentWeather.getMainInstance()!=null){
            weather.setTemprature(currentWeather.getMainInstance().getTemperature());
        }



        return  weather;

    }
}
