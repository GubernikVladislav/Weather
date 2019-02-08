package ru.gubernik.weather.yahoo.service.yahoo;

import java.io.UnsupportedEncodingException;

/**
 * Сервис отправки запроса в Yahoo
 */
public interface YahooService {

    /**
     * Построение и отправка запроса в Yahoo
     * @param location - название города
     * @return - строку json ответа
     */
    String createYahooRequest(String location) throws UnsupportedEncodingException;
}
