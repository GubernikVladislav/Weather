package ru.gubernik.weather.weatherservice.controller;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.weatherservice.view.LocationView;

import java.util.List;

/**
 * Интерфейс контроллера получения погоды
 */
public interface WeatherController {

    /**
     * Получение погоды по названию города
     * @param location - название города
     * @return
     */
    WeatherDto getWeather(String location) throws Exception;

    /**
     * Получение списка городов
     * @return
     */
    List<LocationView> getLocationList();
}
