package ru.gubernik.weather.dbserviceapi.service;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

/**
 * Интерфейс для Remote Proxy
 */
public interface RemoteProxy {

    /**
     * Получение данных о погоде в городе
     * @param location - название города
     * @return - объект c данными о погоде
     */
    WeatherDto getWeather(String location);

}
