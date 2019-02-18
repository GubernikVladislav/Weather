package ru.gubernik.weather.dbmodule.dao.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbmodule.model.Weather;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpringWeatherDaoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private SpringWeatherDaoImpl weatherDaoTest;

    /**
     * Проверка объектов теста на null
     */
    @Test
    public void checkNull(){
        assertNotNull(entityManager);
        assertNotNull(weatherDaoTest);
    }

    /**
     * Тестирование получения данных
     */
    @Test
    public void getTest(){
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        Root root = mock(Root.class);
        Weather weather = mock(Weather.class);
        TypedQuery query = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Weather.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Weather.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(weather);

        weatherDaoTest.get("Moscow");

        verify(entityManager, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Weather.class);
        verify(criteriaQuery, times(1)).from(Weather.class);
        verify(entityManager, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getSingleResult();
    }

    /**
     * Тестирование при null параметре
     */
    @Test
    public void nullTest(){
        String nullLocation = null;

        Weather weather = weatherDaoTest.get(nullLocation);

        assertEquals(weather, new Weather());
    }

    /**
     * Тестирование при пустом параметре
     */
    @Test
    public void emptyTest(){
        String emptyLocation = "";

        Weather weather = weatherDaoTest.get(emptyLocation);

        assertEquals(weather, new Weather());
    }

}
