package ru.gubernik.weather.admin.jms;

/**
 * Интерфейс отправки сообщений
 */
public interface JmsSender {

    /**
     * Отправка сообщения
     * @param message - сообщение для отправки в очередь
     */
    void send(String message);
}
