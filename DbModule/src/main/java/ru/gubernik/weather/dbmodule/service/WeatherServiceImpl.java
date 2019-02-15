package ru.gubernik.weather.dbmodule.service;

import org.springframework.transaction.annotation.Transactional;
import ru.gubernik.weather.dbmodule.dao.WeatherDao;
import ru.gubernik.weather.dbmodule.mapper.MapperFacade;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbserviceapi.model.LocationDto;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherServiceImpl implements WeatherService {

    @Inject
    private WeatherDao weatherDao;

    @Inject
    private MapperFacade mapperFacade;

    public WeatherServiceImpl(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(WeatherDto weatherDto) {

        Weather weather = mapperFacade.map(weatherDto, Weather.class);

        if(locationList().contains(weather.getLocation())){
            update(weatherDto);
        }else {
            weatherDao.save(weather);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(WeatherDto weather) {

        WeatherDto weatherDto = get(weather.getLocation().getCity());
        mapperFacade.map(weather, weatherDto);
        Weather newWeather = mapperFacade.map(weatherDto, Weather.class);
        newWeather.setCityName(weather.getLocation().getCity());
        weatherDao.update(newWeather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDto get(String location) {

        WeatherDto weatherDto;
        Weather weather = weatherDao.get(location);
        weatherDto = mapperFacade.map(weather, WeatherDto.class);

        return weatherDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> locationList() {
        return weatherDao.getLocationList();
    }
}
