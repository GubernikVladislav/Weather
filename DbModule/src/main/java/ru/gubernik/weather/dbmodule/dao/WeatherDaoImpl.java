package ru.gubernik.weather.dbmodule.dao;

import org.springframework.stereotype.Component;
import ru.gubernik.weather.dbmodule.model.Forecast;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;

import javax.enterprise.context.ApplicationScoped;
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
@Component
public class WeatherDaoImpl implements WeatherDao {

    @PersistenceContext(unitName = "Postgres")
    private EntityManager entityManager;

    public WeatherDaoImpl(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Weather weather) {

        weather.getLocation().setWeather(weather);

        weather.setCityName(weather.getLocation().getCity());

        weather.getCurrentObservation().setWeather(weather);
        weather.getCurrentObservation().getAstronomy().setObservation(weather.getCurrentObservation());
        weather.getCurrentObservation().getAtmosphere().setObservation(weather.getCurrentObservation());
        weather.getCurrentObservation().getWind().setObservation(weather.getCurrentObservation());
        weather.getCurrentObservation().getCondition().setObservation(weather.getCurrentObservation());

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
        CriteriaQuery<Weather> criteriaQuery = criteriaBuilder.createQuery(Weather.class);
        Root root = criteriaQuery.from(Weather.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("cityName"), location));

        TypedQuery query = entityManager.createQuery(criteriaQuery);
        Weather weather = (Weather) query.getSingleResult();
        return weather;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> getLocationList() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
        Root root = criteriaQuery.from(Location.class);

        TypedQuery<Location> query = entityManager.createQuery(criteriaQuery);
        List<Location> locations = query.getResultList();

        return locations;
    }
}
