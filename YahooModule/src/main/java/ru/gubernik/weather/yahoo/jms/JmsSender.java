package ru.gubernik.weather.yahoo.jms;

import ru.gubernik.weather.model.Weather;

/**
 * Интерфейс отправки jms сообщения
 */
public interface JmsSender {

    /**
     * Отправка jms сообщения
     * @param weather - объект сообщения
     */
    void send(Weather weather);
}
