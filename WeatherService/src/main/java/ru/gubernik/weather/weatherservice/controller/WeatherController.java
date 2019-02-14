package ru.gubernik.weather.weatherservice.controller;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

/**
 * Интерфейс контроллера получения погоды
 */
public interface WeatherController {

    /**
     * Получение погоды по названию города
     * @param location - название города
     * @return
     */
    WeatherDto getWeather(String location);
}
