package com.example.nick.domoticapp.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.DataFormatException;

public class WeatherAPIContent {
    private static WeatherAPIContent weatherAPIInstance;
    private String name;
    private List<Weather> weather;
    private Main main;
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

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
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

class Weather{
    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String main;
    private String description;
    private String icon;
}

class Main{
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    private String temp;
    private String pressure;
    private String humidity;
}

class Wind{
    private String speed;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}

class System{
    private String country;
    private String sunrise;
    private String sunset;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
