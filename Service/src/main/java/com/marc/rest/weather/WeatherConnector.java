package com.marc.rest.weather;

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
        owm = new OpenWeatherMap("738192430eb7328f8a5b9a7cbdab6b45");
    }

    public void setOpenWeatherMap(OpenWeatherMap owm){
        this.owm = owm;
    }

    public Weather getWeather(String latidude, String longitude) throws IOException {
        CurrentWeather currentWeather = owm.currentWeatherByCoordinates(Float.parseFloat(latidude),Float.parseFloat(longitude));
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
        Weather weather = new Weather(currentWeather.getRainInstance().hasRain(), currentWeather.getMainInstance().getTemperature());
        return  weather;
    }
}
