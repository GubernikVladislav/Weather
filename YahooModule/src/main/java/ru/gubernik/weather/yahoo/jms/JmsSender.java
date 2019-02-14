package ru.gubernik.weather.yahoo.jms;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

/**
 * Интерфейс отправки jms сообщения
 */
public interface JmsSender {

    /**
     * Отправка jms сообщения
     * @param weather - объект сообщения
     */
    void send(WeatherDto weather);
}
