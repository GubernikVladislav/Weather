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

    @Autowired
    private RemoteProxy remoteProxy;

    @Autowired
    private MapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto getWeather(String location) {
        return remoteProxy.getWeather(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LocationView> getLocations() {

        List<LocationDto> locationDto = remoteProxy.getLocations();
        List<LocationView> views = mapperFactory.getMapperFacade().mapAsList(locationDto, LocationView.class);

        return views;
    }
}
