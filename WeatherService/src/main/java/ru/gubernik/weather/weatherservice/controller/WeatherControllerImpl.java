package ru.gubernik.weather.weatherservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.weatherservice.service.WeatherService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * {@inheritDoc}
 */
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class WeatherControllerImpl implements WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherControllerImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/{location}", method = {GET})
    public WeatherDto getWeather(@PathVariable("location") String location){
        return weatherService.getWeather(location);
    }
}
