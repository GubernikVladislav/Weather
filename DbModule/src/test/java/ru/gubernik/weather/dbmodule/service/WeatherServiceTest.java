package ru.gubernik.weather.dbmodule.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbmodule.dao.WeatherDao;
import ru.gubernik.weather.dbmodule.mapper.MapperFacade;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbserviceapi.model.AstronomyDto;
import ru.gubernik.weather.dbserviceapi.model.AtmosphereDto;
import ru.gubernik.weather.dbserviceapi.model.ConditionDto;
import ru.gubernik.weather.dbserviceapi.model.ForecastDto;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.ObservationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.model.WindDto;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Тестирование сервиса
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @Mock
    private WeatherDao weatherDao;

    @Mock
    private MapperFacade mapperFacade;

    @Spy
    @InjectMocks
    private WeatherServiceImpl weatherService;

    private Weather weather;
    private WeatherDto weatherDto;
    private LocationDto location;
    private ObservationDto observation;
    private AstronomyDto astronomy;
    private AtmosphereDto atmosphere;
    private WindDto wind;
    private ForecastDto forecast;
    private ConditionDto condition;
    private List list;
    private Iterator iterator;

    @Before
    public void set(){
        weather = mock(Weather.class);
        weatherDto = mock(WeatherDto.class);
        observation = mock(ObservationDto.class);
        astronomy = mock(AstronomyDto.class);
        atmosphere = mock(AtmosphereDto.class);
        wind = mock(WindDto.class);
        condition = mock(ConditionDto.class);
        location = mock(LocationDto.class);
        list = mock(List.class);
        iterator = mock(Iterator.class);
        forecast = mock(ForecastDto.class);

        when(weatherDto.getLocation()).thenReturn(location);
        when(weatherDto.getCurrentObservation()).thenReturn(observation);
        when(observation.getAstronomy()).thenReturn(astronomy);
        when(observation.getAtmosphere()).thenReturn(atmosphere);
        when(observation.getWind()).thenReturn(wind);
        when(observation.getCondition()).thenReturn(condition);
        when(weatherDto.getForecasts()).thenReturn(list);
        when(list.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(forecast);
        when(weather.getLocation()).thenReturn(mock(Location.class));

    }

    /**
     * Проверка инъекций
     */
    @Test
    public void checkNull(){
        assertNotNull(weatherDao);
        assertNotNull(mapperFacade);
        assertNotNull(weatherService);
    }

    /**
     * Проверка добавление новой информации о погоде
     */
    @Test
    public void saveTest(){

        when(mapperFacade.map(weatherDto, Weather.class)).thenReturn(weather);

        weatherService.save(weatherDto);

        verify(weatherDao, times(1)).save(weather);
    }

    /**
     * Проверка сохранения при null параметре
     */
    @Test
    public void nullSaveTest(){
        WeatherDto weatherDto = null;

        weatherService.save(weatherDto);

        verifyZeroInteractions(weatherDao);
    }

    /**
     * Проверка обновления при null параметре
     */
    @Test
    public void nullUpdateTest(){
        WeatherDto weatherDto = null;

        weatherService.update(weatherDto);

        verifyZeroInteractions(weatherDao);
    }

    /**
     * Проверка получения списка доступных городов
     */
    @Test
    public void listTest(){
        List<Location> list = mock(List.class);

        when(weatherDao.getLocationList()).thenReturn(list);

        assertEquals(list, weatherService.locationList());
        verify(weatherDao, times(1)).getLocationList();
    }
}
