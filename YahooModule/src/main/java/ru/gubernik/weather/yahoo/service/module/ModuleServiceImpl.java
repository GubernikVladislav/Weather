package ru.gubernik.weather.yahoo.service.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.yahoo.jms.JmsSender;
import ru.gubernik.weather.yahoo.service.yahoo.YahooService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

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

        if (location.isEmpty()){
            return;
        }

        String jsonString = yahooService.createYahooRequest(location);
        WeatherDto weather = jsonMap(jsonString);
        sendJms(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto jsonMap(String jsonString) {

        if(jsonString.isEmpty()){
            return new WeatherDto();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherDto weather;
        try {
            weather = objectMapper.readValue(jsonString, WeatherDto.class);
            return weather;
        } catch (IOException e) {
            throw  new RuntimeException("Mapper read error", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendJms(WeatherDto weather) {
        if(weather != null) {
            jmsSender.send(weather);
        }
    }

}
