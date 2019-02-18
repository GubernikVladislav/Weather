package ru.gubernik.weather.yahoo.service.module;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

/**
 * Основной сервис модуля
 */
public interface ModuleService {

    /**
     * Построение запроса в Yahoo
     * @param location - название города
     */
    void request(String location);

    /**
     * Парсинг json строки в java объект
     */
    WeatherDto jsonMap(String jsonString);

    /**
     * Отправка Jms сообщения
     */
    void sendJms(WeatherDto weather);

}
