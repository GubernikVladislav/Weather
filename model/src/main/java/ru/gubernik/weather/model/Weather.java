package ru.gubernik.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
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
}
