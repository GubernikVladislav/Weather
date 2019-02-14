package ru.gubernik.weather.dbmodule.dao;

import ru.gubernik.weather.dbmodule.model.Forecast;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherDaoImpl implements WeatherDao {

    @PersistenceContext(name = "Postgres")
    private EntityManager entityManager;

    public WeatherDaoImpl(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Weather weather) {

        weather.getLocation().setWeather(weather);

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
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> getLocationList() {
        return null;
    }
}
