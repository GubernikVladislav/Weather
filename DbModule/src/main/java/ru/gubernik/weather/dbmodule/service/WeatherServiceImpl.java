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
        Location location = weather.getLocation();
        List<Location> locations = locationList();

        if(locations.contains(location)){
            update(weatherDto);
        }else {
            weatherDao.save(weather);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(WeatherDto weatherDto) {

        Weather weather = weatherDao.get(weatherDto.getLocation().getCity());
        mapperFacade.map(weatherDto, weather);
        weatherDao.update(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> locationList() {
        return weatherDao.getLocationList();
    }
}
