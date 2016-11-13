package com.marc.rest.cache;

import com.marc.rest.weather.Weather;
import org.joda.time.DateTime;

/**
 * Created by marc on 13.11.16.
 */
public class WeatherMeta {

    private Weather weather;
    private DateTime created;

    public WeatherMeta(Weather weather) {
        this.weather = weather;
        this.created = new DateTime();
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;

    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }
}
