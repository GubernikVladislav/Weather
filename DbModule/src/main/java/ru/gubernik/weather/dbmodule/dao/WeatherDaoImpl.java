package ru.gubernik.weather.dbmodule.dao;

import org.springframework.stereotype.Service;
import ru.gubernik.weather.dbmodule.model.Forecast;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class WeatherDaoImpl implements WeatherDao {

    @Resource(mappedName = "java:/entityManager")
    private EntityManager entityManager;

    public WeatherDaoImpl(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Weather weather) {

        if(checkNull(weather)){
            return;
        }

        setDependencies(weather);

        entityManager.persist(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Weather weather) {

        if(checkNull(weather)){
            return;
        }
        entityManager.merge(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Weather get(String location) {

        if(location == null || location.isEmpty()){
            return new Weather();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weather> criteriaQuery = criteriaBuilder.createQuery(Weather.class);
        Root root = criteriaQuery.from(Weather.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("cityName"), location));

        TypedQuery query = entityManager.createQuery(criteriaQuery);
        return (Weather) query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> getLocationList() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);

        criteriaQuery.select(criteriaQuery.from(Location.class));

        TypedQuery<Location> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    /**
     * Проверка null значений в переданном объекте
     * @param weather - проверяемый объект
     * @return true, если найдено null значение, иначе - false
     */
    private boolean checkNull(Weather weather){

        return weather == null || weather.getCurrentObservation() == null
                || weather.getCurrentObservation().getCondition() == null
                || weather.getCurrentObservation().getAtmosphere() == null
                || weather.getCurrentObservation().getAstronomy() == null
                || weather.getCurrentObservation().getWind() == null
                || weather.getForecasts() == null;
    }

    /**
     * Установка зависимостей в зависимые объекты
     * @param weather - объект данных о погоде
     */
    private void setDependencies(Weather weather) {

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
    }
}
