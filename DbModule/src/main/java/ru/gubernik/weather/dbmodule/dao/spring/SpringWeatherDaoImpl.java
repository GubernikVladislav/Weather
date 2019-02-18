package ru.gubernik.weather.dbmodule.dao.spring;

import org.springframework.stereotype.Service;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;
import sun.awt.windows.WEmbeddedFrame;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class SpringWeatherDaoImpl implements SpringWeatherDao {

    private EntityManager entityManager;

    @Resource(mappedName = "java:/yourEntityManagerFactoryName")
    private EntityManagerFactory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Weather get(String location) {
        entityManager = factory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weather> criteriaQuery = criteriaBuilder.createQuery(Weather.class);
        Root root = criteriaQuery.from(Weather.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("cityName"), location));

        TypedQuery query = entityManager.createQuery(criteriaQuery);
        Weather weather = (Weather) query.getSingleResult();

        return weather;
    }

    @Override
    public List<Location> locations() {
        entityManager = factory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
        Root root = criteriaQuery.from(Location.class);

        criteriaQuery.select(root);

        TypedQuery query = entityManager.createQuery(criteriaQuery);
        List<Location> locations = query.getResultList();
        return locations;
    }
}
