package ru.gubernik.weather.dbmodule.dao.spring;

import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;

import java.util.List;

/**
 * Интерфейс Dao для использования со Spring
 */
public interface SpringWeatherDao {

    /**
     * Получение данных о погоде по названию города
     * @param location - название города
     * @return объект с даныыми о погоде
     */
    Weather get(String location);

    /**
     * Получение списка достыпных городов
     * @return список Location
     */
    List<Location> locations();
}
