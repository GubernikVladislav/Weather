package ru.gubernik.weather.dbservice.service;

import ru.gubernik.weather.model.Weather;

/**
 * Сервис модуля базы данных
 */
public interface WeatherService {

    /**
     * Добавление информации о погоде для нового города
     * @param weather
     */
    void save(Weather weather);

    /**
     * Обновление информации о погоде
     * @param weather
     */
    void update(Weather weather);

    /**
     * Получение информации о погоде в городе
     * @param location - название города
     * @return объект Weather с информацией о погоде
     */
    Weather get(String location);
}
