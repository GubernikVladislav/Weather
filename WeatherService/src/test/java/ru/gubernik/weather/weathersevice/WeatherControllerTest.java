package ru.gubernik.weather.weathersevice;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
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

        when(weatherService.replaceCase(location)).thenReturn("Moscow");
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

        when(weatherService.replaceCase(location)).thenReturn(editLocation);
        when(weatherService.list()).thenReturn(list);

        try {
            weatherController.getWeather(location);
        }catch (RuntimeException e){
            assertEquals(e.getMessage(), "incorrect city name");
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

        when(weatherService.replaceCase(location)).thenReturn(editLocation);
        when(weatherService.list()).thenReturn(list);

        try {
            weatherController.getWeather(location);
        }catch (RuntimeException e){
            assertEquals(e.getMessage(), "no information");
        }
    }

    /**
     * Тестирование получение списка доступных городов
     */
    @Test
    public void listTest(){
        List list = mock(List.class);

        when(weatherService.list()).thenReturn(list);

        assertEquals(weatherController.list(), list);
        verify(weatherService, times(1)).list();
    }
}
