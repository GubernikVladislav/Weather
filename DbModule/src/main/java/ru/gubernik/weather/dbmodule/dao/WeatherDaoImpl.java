package ru.gubernik.weather.dbmodule.dao;

import ru.gubernik.weather.model.Weather;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherDaoImpl implements WeatherDao {

    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Weather weather) {
        entityManager.persist(weather);
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
