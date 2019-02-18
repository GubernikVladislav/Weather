package ru.gubernik.weather.weatherservice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.GetWeatherService;
import ru.gubernik.weather.weatherservice.view.LocationView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class WeatherServiceImpl implements WeatherService{

    private final GetWeatherService getWeatherService;
    private final MapperFactory mapperFactory;

    @Autowired
    public WeatherServiceImpl(GetWeatherService getWeatherService, MapperFactory mapperFactory) {
        this.getWeatherService = getWeatherService;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto getWeather(String location) {

        if(location == null || location.isEmpty()){
            return new WeatherDto();
        }
        return getWeatherService.getWeather(replaceCase(location));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LocationView> list() {

        List<LocationDto> dtoList = getWeatherService.list();
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
    public String replaceCase(String s) {

        if (s == null || s.isEmpty()){
            return "";
        }
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
