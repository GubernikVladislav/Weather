package ru.gubernik.weather.weatherservice.service;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.weatherservice.view.LocationView;

import java.util.List;

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

    /**
     * Получение списка достыпных городов
     * @return
     */
    List<LocationView> list();
}
