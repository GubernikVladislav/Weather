package ru.gubernik.weather.dbmodule.service;

import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import java.util.List;

/**
 * Интерфейс модуля базы данных
 */
public interface WeatherService {

    /**
     * Добавить новую информацию о погоде
     * @param weather - объект с информацией
     */
    void save(WeatherDto weather);

    /**
     * Обновить информацию о погоде
     * @param weather - объект с новой информацией о погоде
     */
    void update(WeatherDto weather);

    /**
     * Получение информации о погоде
     * @param location - название города
     * @return WeatherDto - объект с информацией
     */
    WeatherDto get(String location);

    /**
     * Получение списка городов, имеющих информацию о погоде
     * @return
     */
    List<Location> locationList();
}
