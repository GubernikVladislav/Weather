package ru.gubernik.weather.dbmodule.service.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbmodule.dao.WeatherDaoImpl;
import ru.gubernik.weather.dbmodule.model.Astronomy;
import ru.gubernik.weather.dbmodule.model.Atmosphere;
import ru.gubernik.weather.dbmodule.model.Condition;
import ru.gubernik.weather.dbmodule.model.Forecast;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Observation;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbmodule.model.Wind;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Тестирование Дао класса
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherDaoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private WeatherDaoImpl weatherDao;

    /**
     * Проверка инъекций
     */
    @Test
    public void checkNull(){
        assertNotNull(entityManager);
        assertNotNull(weatherDao);
    }

    /**
     * Тестирование метода сохранения данных в БД
     */
    @Test
    public void saveTest(){
        Weather weather = mock(Weather.class);
        Observation observation = mock(Observation.class);
        Astronomy astronomy = mock(Astronomy.class);
        Atmosphere atmosphere = mock(Atmosphere.class);
        Wind wind = mock(Wind.class);
        Condition condition = mock(Condition.class);
        Location location = mock(Location.class);
        List<Forecast> list = mock(List.class);
        Iterator<Forecast> iterator = mock(Iterator.class);
        Forecast forecast = mock(Forecast.class);

        when(weather.getLocation()).thenReturn(location);
        when(weather.getCurrentObservation()).thenReturn(observation);
        when(observation.getAstronomy()).thenReturn(astronomy);
        when(observation.getAtmosphere()).thenReturn(atmosphere);
        when(observation.getWind()).thenReturn(wind);
        when(observation.getCondition()).thenReturn(condition);
        when(weather.getForecasts()).thenReturn(list);
        when(list.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(forecast);

        weatherDao.save(weather);

        verify(location, times(1)).setWeather(weather);
        verify(observation).setWeather(weather);
        verify(astronomy).setObservation(observation);
        verify(atmosphere).setObservation(observation);
        verify(wind).setObservation(observation);
        verify(condition).setObservation(observation);
        verify(entityManager, times(1)).persist(weather);
    }

    /**
     * Тестирование обновления данных
     */
    @Test
    public void updateTest(){
        Weather weather = mock(Weather.class);

        weatherDao.update(weather);

        verify(entityManager, times(1)).merge(weather);
    }
}
