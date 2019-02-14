package ru.gubernik.weather.dbmodule.service;

import org.springframework.transaction.annotation.Transactional;
import ru.gubernik.weather.dbmodule.dao.WeatherDao;
import ru.gubernik.weather.dbmodule.mapper.MapperFacade;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbserviceapi.LocationDto;
import ru.gubernik.weather.dbserviceapi.WeatherDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherServiceImpl implements WeatherService {

    @Inject
    private WeatherDao weatherDao;

    @Inject
    private MapperFacade mapperFacade;

    public WeatherServiceImpl(){

    }

    public WeatherServiceImpl(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    public void weatherDao(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(WeatherDto weatherDto) {

        Weather weather = mapperFacade.map(weatherDto, Weather.class);

        weatherDao.save(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(WeatherDto weather) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto get(String location) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LocationDto> locationList() {
        return null;
    }
}
