package ru.gubernik.weather.yahoo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.model.Weather;
import ru.gubernik.weather.yahoo.jms.JmsSender;
import ru.gubernik.weather.yahoo.service.module.ModuleServiceImpl;
import ru.gubernik.weather.yahoo.service.yahoo.YahooService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.mockito.Mockito.*;

/**
 * Тестирование сервиса модуля Yahoo
 */
@RunWith(MockitoJUnitRunner.class)
public class ModuleServiceTest {

    private String jsonString = "{\"location\":" +
            "{\"woeid\":2122265," +
            "\"city\":\"Moscow\"," +
            "\"region\":\" Moscow Federal City\"," +
            "\"country\":\"Russia\"," +
            "\"lat\":55.741638," +
            "\"long\":37.605061," +
            "\"timezone_id\":\"Europe/Moscow\"" +
            "}," +
            "\"current_observation\":{" +
            "\"wind\":{" +
            "\"chill\":27," +
            "\"direction\":205," +
            "\"speed\":10.56" +
            "}," +
            "\"atmosphere\":{" +
            "\"humidity\":70," +
            "\"visibility\":10.0," +
            "\"pressure\":29.59," +
            "\"rising\":0" +
            "}," +
            "\"astronomy\":{" +
            "\"sunrise\":\"8:09 am\"," +
            "\"sunset\":\"5:20 pm\"" +
            "}," +
            "\"condition\":{" +
            "\"text\":\"Mostly Cloudy\"," +
            "\"code\":28," +
            "\"temperature\":35" +
            "}," +
            "\"pubDate\":1549630800" +
            "}," +
            "\"forecasts\":" +
            "[{\"day\":\"Fri\"," +
            "\"date\":1549573200," +
            "\"low\":26," +
            "\"high\":34," +
            "\"text\":\"Mostly Cloudy\"," +
            "\"code\":28}]}";

    @Mock
    private YahooService yahooService;

    @Mock
    private JmsSender jmsSender;

    @Spy
    @InjectMocks
    private ModuleServiceImpl moduleService;

    /**
     * Проверка объектов теста на null
     */
    @Before
    public void checkNull(){
        Assert.assertNotNull(moduleService);
        Assert.assertNotNull(jmsSender);
        Assert.assertNotNull(yahooService);
    }

    /**
     * Тестирование метода отправки запроса в Yahoo
     */
    @Test
    public void requestTest() throws UnsupportedEncodingException {
        String location = "Moscow";
        Weather weather = mock(Weather.class);

        when(yahooService.createYahooRequest(location)).thenReturn(jsonString);
        when(moduleService.jsonMap(jsonString)).thenReturn(weather);

        moduleService.request(location);

        verify(yahooService, atLeast(1)).createYahooRequest(location);
        verify(moduleService, atLeast(1)).jsonMap(jsonString);
        verify(moduleService, atLeast(1)).sendJms(weather);
    }

    /**
     * Тестирование маппинга json в класс Weather
     */
    @Test
    public void jsonMappingTest() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Weather targetWeather = mapper.readValue(jsonString, Weather.class);

        Weather weather = moduleService.jsonMap(jsonString);

        Assert.assertEquals(weather, targetWeather);

        Assert.assertNotEquals(moduleService.jsonMap(""), targetWeather);
    }

    /**
     * Тестирование отправки объекта Weather через jmsSender
     */
    @Test
    public void sendJmsTest(){

        Weather weather = new Weather();

        moduleService.sendJms(weather);

        verify(jmsSender, times(1)).send(weather);
    }
}