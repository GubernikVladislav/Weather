package ru.gubernik.weather.dbmodule.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gubernik.weather.dbmodule.dao.WeatherDao;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;
import ru.gubernik.weather.dbserviceapi.service.RemoteProxy;

import java.util.List;

/**
 * Реализация интерфейса RemoteProxy
 */
@Service
public class RemoteProxyImpl implements RemoteProxy {

    @Autowired
    private WeatherDao weatherDao;

    @Autowired
    private MapperFactory mapperFactory;

    public RemoteProxyImpl(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto getWeather(String location) {

        Weather weather = weatherDao.get(location);

        try {
            System.out.println("MAPPPPING");
            WeatherDto weatherDto = mapperFactory.getMapperFacade().map(weather, WeatherDto.class);
            return weatherDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WeatherDto();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LocationDto> getLocations() {

        List<Location> locations = weatherDao.getLocationList();
        List<LocationDto> locationDto = mapperFactory.getMapperFacade().mapAsList(locations, LocationDto.class);

        return locationDto;
    }
}
