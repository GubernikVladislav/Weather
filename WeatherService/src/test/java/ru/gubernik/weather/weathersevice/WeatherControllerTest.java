package ru.gubernik.weather.weathersevice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.weatherservice.controller.WeatherControllerImpl;
import ru.gubernik.weather.weatherservice.service.WeatherService;
import ru.gubernik.weather.weatherservice.view.LocationView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестирование класса контроллера
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherControllerImpl weatherController;

    /**
     * Тестирование получение данных
     */
    @Test
    public void getTest(){

        String location = "moscow";

        LocationView view = new LocationView("Moscow");
        List<LocationView> list = new ArrayList<>();
        list.add(view);

        when(weatherService.editString(location)).thenReturn("Moscow");
        when(weatherService.list()).thenReturn(list);

        weatherController.getWeather(location);

        verify(weatherService, times(1)).getWeather(location);
    }

    /**
     * Тестирование неправильного названия города
     */
    @Test
    public void incorrectCityNameTest(){
        String location = "aaaa";
        String editLocation = "Aaaa";

        LocationView view = new LocationView(editLocation);
        List<LocationView> list = new ArrayList<>();
        list.add(view);

        when(weatherService.editString(location)).thenReturn(editLocation);
        when(weatherService.list()).thenReturn(list);

        try {
            weatherController.getWeather(location);
        }catch (RuntimeException e){
            Assert.assertEquals(e.getMessage(), "incorrect city name");
        }
    }

    /**
     * Тестирование отсутствия информации
     */
    @Test
    public void noInformationTest(){
        String location = "berlin";
        String editLocation = "Berlin";

        LocationView view = new LocationView(editLocation);
        List<LocationView> list = new ArrayList<>();

        when(weatherService.editString(location)).thenReturn(editLocation);
        when(weatherService.list()).thenReturn(list);

        try {
            weatherController.getWeather(location);
        }catch (RuntimeException e){
            Assert.assertEquals(e.getMessage(), "no information");
        }
    }
}
