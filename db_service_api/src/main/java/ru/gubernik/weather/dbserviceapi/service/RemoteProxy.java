package ru.gubernik.weather.dbserviceapi.service;

import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import java.util.List;

/**
 * нтерфейс для Remote Proxy
 */
public interface RemoteProxy {

    /**
     * Получение данных о погоде в городе
     * @param location - название города
     * @return - объект c данными о погоде
     */
    WeatherDto getWeather(String location);

    /**
     * Получение списка доступных городов
     * @return
     */
    List<LocationDto> list();
}
