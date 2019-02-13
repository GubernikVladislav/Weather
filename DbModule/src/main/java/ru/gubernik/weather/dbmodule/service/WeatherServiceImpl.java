package ru.gubernik.weather.dbmodule.service;

import org.springframework.transaction.annotation.Transactional;
import ru.gubernik.weather.dbmodule.dao.WeatherDao;
import ru.gubernik.weather.model.Location;
import ru.gubernik.weather.model.Weather;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherServiceImpl implements WeatherService {

    @Inject
    private WeatherDao weatherDao;

    public WeatherServiceImpl(){

    }

    public void weatherDao(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(Weather weather) {
        weatherDao.save(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Weather weather) {
        weatherDao.update(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Weather get(String location) {
        return weatherDao.get(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> locationList() {
        return new ArrayList<Location>();
    }
}
