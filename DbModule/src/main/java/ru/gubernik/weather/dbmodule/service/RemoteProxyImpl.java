package ru.gubernik.weather.dbmodule.service;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.RemoteProxy;

/**
 * Реализация интерфейса Remote Proxy
 */
public class RemoteProxyImpl implements RemoteProxy {

    public RemoteProxyImpl(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto getWeather(String location) {

        return new WeatherDto();
    }
}
