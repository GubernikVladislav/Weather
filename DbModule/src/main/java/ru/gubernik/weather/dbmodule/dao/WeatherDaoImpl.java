package ru.gubernik.weather.dbmodule.dao;

import ru.gubernik.weather.dbmodule.model.Forecast;
import ru.gubernik.weather.dbmodule.model.Weather;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherDaoImpl implements WeatherDao {

    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext(name = "Postgres")
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Weather weather) {

        weather.getCurrentObservation().getWind().setObservation(weather.getCurrentObservation());
        weather.getCurrentObservation().getAstronomy().setObservation(weather.getCurrentObservation());
        weather.getCurrentObservation().getAtmosphere().setObservation(weather.getCurrentObservation());
        weather.getCurrentObservation().getCondition().setObservation(weather.getCurrentObservation());

        weather.getCurrentObservation().setWeather(weather);
        weather.getLocation().setWeather(weather);
        weather.setCityName(weather.getLocation().getCity());

        for(Forecast forecast : weather.getForecasts()){
            forecast.setWeather(weather);
        }

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

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Weather.class);
        Root root = criteriaQuery.from(Weather.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("location"), location ));

        TypedQuery query = entityManager.createQuery(criteriaQuery);
        Weather weather = (Weather) query.getSingleResult();
        return weather;
    }
}
