package ru.gubernik.weather.dbmodule.service;

import ru.gubernik.weather.dbmodule.dao.WeatherDao;
import ru.gubernik.weather.dbmodule.mapper.CustomMapperFactory;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.RemoteProxy;

import javax.inject.Inject;

/**
 * Реализация интерфейса Remote Proxy
 */
public class RemoteProxyImpl implements RemoteProxy {

    @Inject
    private WeatherDao weatherDao;

    @Inject
    private CustomMapperFactory mapperFactory;

    public RemoteProxyImpl(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto getWeather(String location) {

        Weather weather = weatherDao.get(location);

        WeatherDto weatherDto;
        try {
            weatherDto = mapperFactory.getObject().getMapperFacade().map(weather, WeatherDto.class);
        } catch (Exception e) {
            throw  new RuntimeException("Mapper error", e);
        }

        return weatherDto;
    }
}
