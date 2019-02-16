package ru.gubernik.weather.weatherservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.weatherservice.service.WeatherService;
import ru.gubernik.weather.weatherservice.view.LocationView;

import java.util.List;

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
    public WeatherDto getWeather(@PathVariable("location") String location) throws Exception {
        if(!location.matches("^[a-zA-Z]+[\\-]?[a-zA-Z]+[\\-]?[a-zA-Z]+$")){
            throw new Exception("incorrect city name");
        }else if(getLocationList().contains(new LocationView(location))){
            return weatherService.getWeather(location);
        }else {
            throw new Exception("no information, see /locations");
        }
    }

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/locations", method = {GET})
    public List<LocationView> getLocationList(){
        return  weatherService.getLocations();
    }

    @ExceptionHandler(Exception.class)
    private String checkExc(Exception e){
        return "{\"error\":\"" + e.getMessage() + "\"}";
    }
}
