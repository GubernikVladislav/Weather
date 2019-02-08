package ru.gubernik.weather.yahoo.service.module;

import ru.gubernik.weather.model.Weather;

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
    Weather jsonMap(String jsonString);

    /**
     * Отправка Jms сообщения
     */
    void sendJms(Weather weather);
}
