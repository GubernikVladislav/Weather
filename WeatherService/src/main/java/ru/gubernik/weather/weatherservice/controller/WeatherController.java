package ru.gubernik.weather.weatherservice.controller;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

/**
 * нтерфейс контроллера получения погоды
 */
public interface WeatherController {

    /**
     * Получение погоды по названию города
     * @param location - название города
     * @return
     */
    WeatherDto getWeather(String location) throws Exception;

}
