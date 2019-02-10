package ru.gubernik.weather.dbmodule.dao;

import ru.gubernik.weather.model.Weather;

import javax.enterprise.context.ApplicationScoped;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherDaoImpl implements WeatherDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Weather weather) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Weather weather) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Weather get(String location) {
        return null;
    }
}
