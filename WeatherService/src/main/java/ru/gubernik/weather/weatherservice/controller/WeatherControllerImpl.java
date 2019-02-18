package ru.gubernik.weather.weatherservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.weatherservice.service.WeatherService;
import ru.gubernik.weather.weatherservice.view.LocationView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * {@inheritDoc}
 */
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class WeatherControllerImpl implements WeatherController {

    private final WeatherService weatherService;
    private Pattern pattern = Pattern.compile("^[a-zA-Z]+[\\-]?[a-zA-Z]+[\\-]?[a-zA-Z]+$");

    @Autowired
    public WeatherControllerImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/{location}", method = {GET})
    public WeatherDto getWeather(@PathVariable("location") String location){

        String editLocation = weatherService.replaceCase(location);
        LocationView view = new LocationView(editLocation);
        Matcher matcher = pattern.matcher(location);

        if(!matcher.matches()){
            throw new RuntimeException("incorrect city name");
        }else if(!list().contains(view)){
            throw new RuntimeException("no information");
        }else {
            return weatherService.getWeather(location);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/locations", method = {GET})
    public List<LocationView> list() {
        return weatherService.list();
    }
}
