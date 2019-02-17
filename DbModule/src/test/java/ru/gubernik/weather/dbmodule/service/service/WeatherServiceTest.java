package ru.gubernik.weather.dbmodule.service.service;

import org.junit.Assert;
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
import ru.gubernik.weather.dbmodule.service.WeatherServiceImpl;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

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

        Weather weather = mock(Weather.class);
        WeatherDto weatherDto = mock(WeatherDto.class);

        when(mapperFacade.map(weatherDto, Weather.class)).thenReturn(weather);

        weatherService.save(weatherDto);

        verify(weatherDao, times(1)).save(weather);
    }

    @Test
    public void updateTest(){
        Weather weather = mock(Weather.class);
        Location location = mock(Location.class);
        WeatherDto weatherDto = mock(WeatherDto.class);
        List list = mock(List.class);
        LocationDto locationDto = mock(LocationDto.class);
        String city = "Moscow";

        when(mapperFacade.map(weatherDto, Weather.class)).thenReturn(weather);
        when(weather.getLocation()).thenReturn(location);
        when(weatherService.locationList()).thenReturn(list);
        when(list.contains(location)).thenReturn(true);

        when(weatherDto.getLocation()).thenReturn(locationDto);
        when(locationDto.getCity()).thenReturn(city);
        when(weatherDao.get(city)).thenReturn(weather);

        weatherService.save(weatherDto);

        verify(weatherService, times(1)).update(weatherDto);
        verify(weatherDto, times(1)).getLocation();
        verify(locationDto, times(1)).getCity();
        verify(weatherDao, times(1)).get(city);
        verify(weatherDao, times(1)).update(weather);
    }
}
