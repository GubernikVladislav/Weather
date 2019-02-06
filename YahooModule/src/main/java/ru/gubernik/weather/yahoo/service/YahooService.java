package ru.gubernik.weather.yahoo.service;

import java.io.UnsupportedEncodingException;

/**
 * Интерфейс для работы с Yahoo
 */
public interface YahooService {

    /**
     * Метод построения запроса
     * @param location - название города
     */
    void request(String location) throws UnsupportedEncodingException;
}
