package ru.gubernik.weather.weatherservice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.RemoteProxy;
import ru.gubernik.weather.weatherservice.view.LocationView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class WeatherServiceImpl implements WeatherService{

    private final RemoteProxy remoteProxy;

    private final MapperFactory mapperFactory;

    @Autowired
    public WeatherServiceImpl(RemoteProxy remoteProxy, MapperFactory mapperFactory) {
        this.remoteProxy = remoteProxy;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto getWeather(String location) {

        location = location.substring(0,1).toUpperCase() + location.substring(1).toLowerCase();

        return remoteProxy.getWeather(location);
    }

    @Override
    public List<LocationView> list() {

        List<LocationDto> dtoList = remoteProxy.list();
        List<LocationView> views = mapperFactory.getMapperFacade().mapAsList(dtoList, LocationView.class);
        return views;
    }
}
