package ru.gubernik.weather.weatherservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class WeatherController {

    @RequestMapping(value = "/{location}", method = {GET})
    public WeatherDto getWeather(@PathVariable("location") String location){
        return new WeatherDto();
    }
}
