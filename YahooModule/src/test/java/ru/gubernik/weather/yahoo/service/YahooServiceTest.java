package ru.gubernik.weather.yahoo.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.gubernik.weather.yahoo.service.yahoo.YahooService;
import ru.gubernik.weather.yahoo.service.yahoo.YahooServiceImpl;

import java.io.UnsupportedEncodingException;

public class YahooServiceTest {

    private YahooService yahooService = new YahooServiceImpl();

    @Before
    public void checkNull(){
        Assert.assertNotNull(yahooService);
    }

    @Test
    public void createRequestTest() throws UnsupportedEncodingException {

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
}
