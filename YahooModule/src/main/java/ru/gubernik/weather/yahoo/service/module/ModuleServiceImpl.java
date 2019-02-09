package ru.gubernik.weather.yahoo.service.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gubernik.weather.model.Weather;
import ru.gubernik.weather.yahoo.jms.JmsSender;
import ru.gubernik.weather.yahoo.service.yahoo.YahooService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class ModuleServiceImpl implements ModuleService {

    private final YahooService yahooService;
    private final JmsSender jmsSender;
    private static Logger log = Logger.getLogger(ModuleServiceImpl.class.getName());

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
            print("REQUEST");
            jsonString = yahooService.createYahooRequest(location);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Weather weather = jsonMap(jsonString);
        sendJms(jsonMap(jsonString));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Weather jsonMap(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather;
        try {
            print("START MAPPING");
            weather = objectMapper.readValue(jsonString, Weather.class);
            print("MAPPING SUCCESS");
            return weather;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Weather() ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendJms(Weather weather) {
        print("SEND JMS");
        jmsSender.send(weather);
        print("SEND SUCCESS");
    }

    @Override
    public void print(String s) {
        log.info(s);
    }

}
