package ru.gubernik.weather.dbmodule.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gubernik.weather.dbmodule.dao.spring.SpringWeatherDao;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.GetWeatherService;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class GetWeatherServiceImpl implements GetWeatherService {

    private final SpringWeatherDao weatherDao;
    private final MapperFactory mapperFactory;

    @Autowired
    public GetWeatherServiceImpl(SpringWeatherDao weatherDao, MapperFactory mapperFactory){
        this.weatherDao = weatherDao;
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

        Weather weather = weatherDao.get(location);

        try {
            return mapperFactory.getMapperFacade().map(weather, WeatherDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Mapper Error");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LocationDto> list() {

        List<Location> locations = weatherDao.locations();
        return mapperFactory.getMapperFacade().mapAsList(locations, LocationDto.class);
    }
}
