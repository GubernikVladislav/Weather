package ru.gubernik.weather.dbmodule.dao.spring;

import org.springframework.stereotype.Service;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;

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

    @Resource(mappedName = "java:/entityManagerFactory")
    private EntityManagerFactory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Weather get(String location) {

        if (location.isEmpty()){
            return new Weather();
        }

        entityManager = factory.createEntityManager();

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
    public List<Location> locations() {
        entityManager = factory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);

        criteriaQuery.select(criteriaQuery.from(Location.class));

        TypedQuery query = entityManager.createQuery(criteriaQuery);
        return (List<Location>) query.getResultList();
    }
}
