package ru.gubernik.weather.dbmodule.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbmodule.dao.WeatherDao;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестироваение реализации интерфейса GetWeatherService
 */
@RunWith(MockitoJUnitRunner.class)
public class GetWeatherServiceTest {

    @Mock
    private WeatherDao weatherDao;

    @Mock
    private MapperFactory mapperFactory;

    @Spy
    @InjectMocks
    private GetWeatherServiceImpl remoteProxy;

    /**
     * Проверка объектов теста на null
     */
    @Test
    public void checkNull(){
        assertNotNull(weatherDao);
        assertNotNull(mapperFactory);
        assertNotNull(remoteProxy);
    }

    /**
     * Тестирование получения погоды
     */
    @Test
    public void getWeather(){
        Weather weather = mock(Weather.class);
        String location = "Moscow";
        WeatherDto weatherDto = mock(WeatherDto.class);
        MapperFacade mapperFacade = mock(MapperFacade.class);

        when(weatherDao.get(location)).thenReturn(weather);
        when(mapperFactory.getMapperFacade()).thenReturn(mapperFacade);
        when(mapperFacade.map(weather, WeatherDto.class)).thenReturn(weatherDto);

        WeatherDto weatherDto1 = remoteProxy.getWeather(location);

        verify(weatherDao).get(location);
        verify(mapperFactory).getMapperFacade();
        verify(mapperFacade).map(weather, WeatherDto.class);

        assertEquals(weatherDto, weatherDto1);
    }

    /**
     * Тестирование при null параметре
     */
    @Test
    public void nullTest(){
        String nullLocation = null;

        WeatherDto weatherDto = remoteProxy.getWeather(nullLocation);

        assertEquals(weatherDto, new WeatherDto());
    }

    /**
     * Тестирование при пустом параметре
     */
    @Test
    public void emptyTest(){
        String emptyLocation = "";

        WeatherDto weatherDto = remoteProxy.getWeather(emptyLocation);

        assertEquals(weatherDto, new WeatherDto());
    }

    /**
     * Тестирование получения списка доступных городов
     */
    @Test
    public void listTest(){
        List<Location> list = mock(List.class);
        List<LocationDto> dtoList = mock(List.class);
        MapperFacade mapperFacade = mock(MapperFacade.class);

        when(weatherDao.getLocationList()).thenReturn(list);
        when(mapperFactory.getMapperFacade()).thenReturn(mapperFacade);
        when(mapperFacade.mapAsList(list, LocationDto.class)).thenReturn(dtoList);

        List<LocationDto> targetList = remoteProxy.list();

        verify(weatherDao, times(1)).getLocationList();
        verify(mapperFactory, times(1)).getMapperFacade();
        verify(mapperFacade, times(1)).mapAsList(list, LocationDto.class);

        assertEquals(targetList, dtoList);
    }
}
