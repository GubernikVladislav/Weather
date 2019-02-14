package ru.gubernik.weather.weatherservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.RemoteProxy;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private RemoteProxy remoteProxy;


    @Override
    public WeatherDto getWeather(String location) {
        return remoteProxy.getWeather(location);
    }
}
