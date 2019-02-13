package ru.gubernik.weather.dbmodule.service;

import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;

import java.util.List;

/**
 * Интерфейс модуля базы данных
 */
public interface WeatherService {

    /**
     * Добавить новую информацию о погоде
     * @param weather - объект с информацией
     */
    void save(Weather weather);

    /**
     * Обновить информацию о погоде
     * @param weather - объект с новой информацией о погоде
     */
    void update(Weather weather);

    /**
     * Получение информации о погоде
     * @param location - название города
     * @return Weather - объект с информацией
     */
    Weather get(String location);

    /**
     * Получение списка городов, имеющих информацию о погоде
     * @return
     */
    List<Location> locationList();
}
