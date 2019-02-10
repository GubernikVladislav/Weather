package ru.gubernik.weather.dbmodule.dao;

import ru.gubernik.weather.model.Weather;

/**
 * Dao для работы с базой данных о погоде
 */
public interface WeatherDao {

    /**
     * Добавление в базу данных новой информации о погоде
     * @param weather - объект информации о погоде
     */
    void save(Weather weather);

    /**
     * Обновение информации о погоде
     * @param weather - объект с новой информацией о погоде
     */
    void update(Weather weather);

    /**
     * Получение информации о погоде по названию города
     * @param location - название города
     * @return Weather объект с инфрмацией о погоде
     */
    Weather get(String location);
}
