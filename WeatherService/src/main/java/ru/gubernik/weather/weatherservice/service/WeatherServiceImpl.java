package ru.gubernik.weather.weatherservice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.RemoteProxy;
import ru.gubernik.weather.weatherservice.view.LocationView;

import java.util.ArrayList;
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

        if(location.isEmpty()){
            return new WeatherDto();
        }

        return remoteProxy.getWeather(editString(location));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LocationView> list() {

        List<LocationDto> dtoList = remoteProxy.list();
        List<LocationView> views = new ArrayList<>();

        for (LocationDto dto : dtoList){
            views.add(new LocationView(dto.getCity()));
        }

        return views;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String editString(String s) {

        if (s.isEmpty()){
            return "";
        }
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
