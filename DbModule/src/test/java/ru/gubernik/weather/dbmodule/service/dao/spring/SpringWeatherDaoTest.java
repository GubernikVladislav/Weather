package ru.gubernik.weather.dbmodule.service.dao.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbmodule.dao.spring.SpringWeatherDaoImpl;
import ru.gubernik.weather.dbmodule.model.Weather;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SpringWeatherDaoTest {

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @InjectMocks
    private SpringWeatherDaoImpl weatherDaoTest;

    @Test
    public void checkNull(){
        assertNotNull(entityManagerFactory);
        assertNotNull(weatherDaoTest);
    }

    @Test
    public void getTest(){
        EntityManager entityManager = mock(EntityManager.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Weather> criteriaQuery = mock(CriteriaQuery.class);
        Root root = mock(Root.class);
        Weather weather = mock(Weather.class);
        TypedQuery query = mock(TypedQuery.class);

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Weather.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Weather.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(weather);

        weatherDaoTest.get("");

        verify(entityManager, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Weather.class);
        verify(criteriaQuery, times(1)).from(Weather.class);
        verify(entityManager, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getSingleResult();
    }
}
