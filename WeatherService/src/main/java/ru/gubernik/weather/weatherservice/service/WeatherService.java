package ru.gubernik.weather.weatherservice.service;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.weatherservice.view.LocationView;

import java.util.List;

/**
 * Сервис получения погоды
 */
public interface WeatherService {

    /**
     * Получение погоды по названию города
     * @param location название города
     * @return объект с данными о погоде
     */
    WeatherDto getWeather(String location);

    /**
     * Получение списка доступных городов
     * @return List список названий городов
     */
    List<LocationView> list();

    /**
     * Преобразование строки к нужному виду. Первая буква заглавная, остальные строчный
     */
    String replaceCase(String s);
}
