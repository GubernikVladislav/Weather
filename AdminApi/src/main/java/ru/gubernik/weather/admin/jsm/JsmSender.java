package ru.gubernik.weather.admin.jsm;

/**
 * Интерфейс отправки сообщений
 */
public interface JsmSender {

    /**
     * Отправка сообщения
     * @param message - сообщение для отправки в очередь
     */
    void send(String message);
}
