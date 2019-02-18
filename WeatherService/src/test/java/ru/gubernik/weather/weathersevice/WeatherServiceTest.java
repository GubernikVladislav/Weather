package ru.gubernik.weather.weathersevice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.GetWeatherService;
import ru.gubernik.weather.weatherservice.service.WeatherServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Тестирование сервиса получения данных о погоде
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @Mock
    private GetWeatherService getWeatherService;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    /**
     * Тестирование получения погоды по называнию города
     */
    @Test
    public void getMethodTest(){

        String location = "Moscow";

        when(getWeatherService.getWeather(location)).thenReturn(new WeatherDto());

        weatherService.getWeather(location);

        verify(getWeatherService, times(1)).getWeather(location);
        Assert.assertNotNull(weatherService.getWeather(location));
    }

    /**
     * Тестирование при null параметре
     */
    @Test
    public void nullGetTest(){
        String nullLocation = null;

        assertEquals(weatherService.getWeather(nullLocation), new WeatherDto());
        verifyZeroInteractions(getWeatherService);
    }

    /**
     * Тестирование при пустом параметре
     */
    @Test
    public void emptyTest(){
        String emptyLocation = "";

        assertEquals(weatherService.getWeather(emptyLocation), new WeatherDto());
        verifyZeroInteractions(getWeatherService);
    }
}
