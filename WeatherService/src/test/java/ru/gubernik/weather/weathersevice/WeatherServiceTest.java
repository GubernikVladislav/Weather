package ru.gubernik.weather.weathersevice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.RemoteProxy;
import ru.gubernik.weather.weatherservice.service.WeatherServiceImpl;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @Mock
    private RemoteProxy remoteProxy;

    @InjectMocks
    private WeatherServiceImpl weatherService;
    @Test
    public void getMethodTest(){

        /*String location = "Moscow";

        when(remoteProxy.getWeather(location)).thenReturn(new WeatherDto());

        weatherService.getWeather(location);

        verify(remoteProxy, times(1)).getWeather(location);
        Assert.assertNotNull(weatherService.getWeather(location));*/
    }
}
