package ru.gubernik.weather.weatherservice.service;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

/**
 * Сервис получения погоды
 */
public interface WeatherService {

    /**
     * Получение погоды по названию города
     * @param location название города
     * @return
     */
    WeatherDto getWeather(String location);
}
