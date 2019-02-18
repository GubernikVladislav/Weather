package ru.gubernik.weather.dbmodule.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbmodule.dao.spring.SpringWeatherDaoImpl;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProxyServiceTest {

    @Mock
    private SpringWeatherDaoImpl springWeatherDao;

    @Mock
    private MapperFactory mapperFactory;

    @Spy
    @InjectMocks
    private RemoteProxyImpl remoteProxy;

    @Test
    public void checkNull(){
        assertNotNull(springWeatherDao);
        assertNotNull(mapperFactory);
        assertNotNull(remoteProxy);
    }

    @Test
    public void getWeather(){
        Weather weather = mock(Weather.class);
        String location = "Moscow";
        WeatherDto weatherDto = mock(WeatherDto.class);
        MapperFacade mapperFacade = mock(MapperFacade.class);

        when(springWeatherDao.get(location)).thenReturn(weather);
        when(mapperFactory.getMapperFacade()).thenReturn(mapperFacade);
        when(mapperFacade.map(weather, WeatherDto.class)).thenReturn(weatherDto);

        WeatherDto weatherDto1 = remoteProxy.getWeather(location);

        verify(springWeatherDao).get(location);
        verify(mapperFactory).getMapperFacade();
        verify(mapperFacade).map(weather, WeatherDto.class);

        assertEquals(weatherDto, weatherDto);
    }
}
