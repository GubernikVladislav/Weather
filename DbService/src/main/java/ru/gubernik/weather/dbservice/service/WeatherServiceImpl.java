package ru.gubernik.weather.dbservice.service;

import org.springframework.transaction.annotation.Transactional;
import ru.gubernik.weather.dbservice.dao.WeatherDao;
import ru.gubernik.weather.model.Weather;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * {@inheritDoc}
 */
@Dependent
public class WeatherServiceImpl implements WeatherService {

    private final WeatherDao weatherDao;

    @Inject
    public WeatherServiceImpl(WeatherDao weatherDao) {
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
    @Transactional
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
}
