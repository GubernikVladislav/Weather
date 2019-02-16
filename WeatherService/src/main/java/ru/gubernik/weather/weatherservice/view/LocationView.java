package ru.gubernik.weather.weatherservice.view;

public class LocationView {

    String city;

    public LocationView(String cityName){
        city = cityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
