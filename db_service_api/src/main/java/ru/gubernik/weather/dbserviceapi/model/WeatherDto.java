package ru.gubernik.weather.dbserviceapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Модель погоды по шаблону ответа Yahoo WeatherDto
 */
public class WeatherDto implements Serializable {

    /**
     * Город
     */
    private LocationDto location;

    /**
     * Текущая погода
     */
    @JsonProperty("current_observation")
    private ObservationDto currentObservation;

    /**
     * Прогноз на неделю
     */
    private List<ForecastDto> forecasts;

    public WeatherDto() {
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public ObservationDto getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(ObservationDto currentObservation) {
        this.currentObservation = currentObservation;
    }

    public List<ForecastDto> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastDto> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public String toString() {
        return "WeatherDto{" +
                "location=" + location +
                ", currentObservation=" + currentObservation +
                ", forecasts=" + forecasts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherDto weather = (WeatherDto) o;
        return Objects.equals(location, weather.location) &&
                Objects.equals(currentObservation, weather.currentObservation) &&
                Objects.equals(forecasts, weather.forecasts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, currentObservation, forecasts);
    }
}
