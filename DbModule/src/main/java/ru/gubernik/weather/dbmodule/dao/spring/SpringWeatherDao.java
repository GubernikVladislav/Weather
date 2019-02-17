package ru.gubernik.weather.dbmodule.dao.spring;

import ru.gubernik.weather.dbmodule.model.Weather;

/**
 * Интерфейс Dao для использования со Spring
 */
public interface SpringWeatherDao {

    /**
     * Получение данных о погоде по названию города
     * @param location - название города
     * @return
     */
    Weather get(String location);
}
