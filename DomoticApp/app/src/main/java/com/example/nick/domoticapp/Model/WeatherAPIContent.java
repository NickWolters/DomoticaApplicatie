package com.example.nick.domoticapp.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.DataFormatException;

public class WeatherAPIContent {
    private static WeatherAPIContent weatherAPIInstance;
    private String name;
    private List<Weather> weather;
    private WeatherMain main;
    private Wind wind;
    private System system;


    public WeatherAPIContent(){

    }

    public static WeatherAPIContent getWeatherAPIInstance() {
        return weatherAPIInstance;
    }

    public static void setWeatherAPIInstance(WeatherAPIContent weatherAPIInstance) {
        WeatherAPIContent.weatherAPIInstance = weatherAPIInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }
}

