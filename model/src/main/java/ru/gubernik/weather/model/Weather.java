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
}
