package ru.gubernik.weather.yahoo.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.gubernik.weather.yahoo.service.yahoo.YahooService;
import ru.gubernik.weather.yahoo.service.yahoo.YahooServiceImpl;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование сервиса отправки запроса в Yahoo
 */
public class YahooServiceTest {

    private YahooService yahooService = new YahooServiceImpl();

    @Before
    public void checkNull(){
        Assert.assertNotNull(yahooService);
    }

    /**
     * Тестирование отправки запроса
     */
    @Test
    public void createRequestTest() {

        String location = "Moscow";

        String response = yahooService.createYahooRequest(location);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.contains("location"));
        Assert.assertTrue(response.contains("wind"));
        Assert.assertTrue(response.contains("current_observation"));
        Assert.assertTrue(response.contains("forecasts"));
        Assert.assertTrue(response.contains("atmosphere"));
        Assert.assertTrue(response.contains("astronomy"));
        Assert.assertTrue(response.contains("condition"));
    }

    /**
     * Тестирование игнорирования пустой строки
     */
    @Test
    public void ignoreEmptyString(){
        String location = "";

        String response = yahooService.createYahooRequest(location);

        assertEquals(response, location);
    }

    /**
     * Тестирование при null параметре
     */
    @Test
    public void ignoreNullString(){
        String location = null;

        String response = yahooService.createYahooRequest(location);

        assertEquals(response, "");
    }
}
