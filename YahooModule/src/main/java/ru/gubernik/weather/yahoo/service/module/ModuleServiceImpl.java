package ru.gubernik.weather.yahoo.service.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gubernik.weather.model.Weather;
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
        sendJms(jsonMap(jsonString));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Weather jsonMap(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Weather weather = objectMapper.readValue(jsonString, Weather.class);
            return weather;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Weather();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendJms(Weather weather) {
        jmsSender.send(weather);
    }
}
