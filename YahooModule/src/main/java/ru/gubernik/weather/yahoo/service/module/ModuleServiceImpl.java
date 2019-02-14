package ru.gubernik.weather.yahoo.service.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gubernik.weather.dbserviceapi.WeatherDto;
import ru.gubernik.weather.yahoo.jms.JmsSender;
import ru.gubernik.weather.yahoo.service.yahoo.YahooService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class ModuleServiceImpl implements ModuleService {

    private final YahooService yahooService;
    private final JmsSender jmsSender;

    @Inject
    public ModuleServiceImpl(YahooService yahooService, JmsSender jmsSender) {
        this.yahooService = yahooService;
        this.jmsSender = jmsSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void request(String location) {
        String jsonString = null;
        try {
            jsonString = yahooService.createYahooRequest(location);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        WeatherDto weather = jsonMap(jsonString);
        sendJms(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto jsonMap(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherDto weather;
        try {
            weather = objectMapper.readValue(jsonString, WeatherDto.class);
            return weather;
        } catch (IOException e) {
            return new WeatherDto();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendJms(WeatherDto weather) {
        jmsSender.send(weather);
    }

}
