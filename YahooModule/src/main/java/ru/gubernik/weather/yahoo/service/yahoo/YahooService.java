package ru.gubernik.weather.yahoo.service.yahoo;

/**
 * Сервис отправки запроса в Yahoo
 */
public interface YahooService {

    /**
     * Построение и отправка запроса в Yahoo
     * @param location - название города
     * @return - строку json ответа
     */
    String createYahooRequest(String location);
}
