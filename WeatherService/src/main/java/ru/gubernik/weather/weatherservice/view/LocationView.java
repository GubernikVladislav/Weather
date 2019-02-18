package ru.gubernik.weather.weatherservice.view;

import java.util.Objects;

/**
 * Представление названия города
 */
public class LocationView {

    private String city;

    public LocationView(String cityName){
        city = cityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationView view = (LocationView) o;
        return Objects.equals(city, view.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city);
    }
}
