package ru.gubernik.weather.dbserviceapi.service;

import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import java.util.List;

/**
 * Интерфейс для передачи через Hessian
 */
public interface RemoteProxy {

    /**
     * Получение данных о погоде по названию города
     * @param location - название города
     * @return - объект с данными о погоде
     */
    WeatherDto getWeather(String location);

    /**
     * Получение списка доступных городов
     * @return - List<LocationDto>
     */
    List<LocationDto> list();
}
