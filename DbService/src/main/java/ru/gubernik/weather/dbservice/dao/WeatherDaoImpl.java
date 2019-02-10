package ru.gubernik.weather.dbservice.dao;

import ru.gubernik.weather.model.Weather;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherDaoImpl implements WeatherDao {

    private final EntityManager entityManager;

    @Inject
    public WeatherDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
        entityManager.merge(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Weather get(String location) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weather> criteriaQuery = criteriaBuilder.createQuery(Weather.class);
        Root root = criteriaQuery.from(Weather.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("cityName"), location));

        TypedQuery<Weather> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
