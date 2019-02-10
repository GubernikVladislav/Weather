package ru.gubernik.weather.dbservice.dao;

import ru.gubernik.weather.model.Weather;

/**
 * Dao для работы с базой данных о погоде
 */
public interface WeatherDao {

    /**
     * Добавление новой информации о погоде
     * @param weather - добавляемый объект
     */
    void save(Weather weather);

    /**
     * Обновление информации о погоде
     * @param weather - обновляемый объект
     */
    void update(Weather weather);

    /**
     * Получение информации о погоде по названию города
     * @param location - название города
     * @return объект Weather
     */
    Weather get(String location);
}
