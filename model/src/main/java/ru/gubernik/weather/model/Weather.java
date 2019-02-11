package ru.gubernik.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Модель погоды по шаблону ответа Yahoo Weather
 */
public class Weather implements Serializable {

    /**
     * Город
     */
    private Location location;

    /**
     * Текущая погода
     */
    @JsonProperty("current_observation")
    private Observation currentObservation;

    /**
     * Прогноз на неделю
     */
    private List<Forecast> forecasts;

    public Weather() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Observation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(Observation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "location=" + location +
                ", currentObservation=" + currentObservation +
                ", forecasts=" + forecasts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(location, weather.location) &&
                Objects.equals(currentObservation, weather.currentObservation) &&
                Objects.equals(forecasts, weather.forecasts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, currentObservation, forecasts);
    }
}
