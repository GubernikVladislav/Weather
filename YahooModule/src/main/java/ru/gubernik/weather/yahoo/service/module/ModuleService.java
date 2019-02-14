package ru.gubernik.weather.yahoo.service.module;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

/**
 * Сервис
 */
public interface ModuleService {

    /**
     * Построение запроса в Yahoo
     * @param location
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
