package ru.gubernik.weather.dbmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gubernik.weather.dbmodule.dao.WeatherDao;
import ru.gubernik.weather.dbmodule.mapper.MapperFacade;
import ru.gubernik.weather.dbmodule.model.Location;
import ru.gubernik.weather.dbmodule.model.Weather;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherDao weatherDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public WeatherServiceImpl(WeatherDao weatherDao, MapperFacade mapperFacade){
        this.weatherDao = weatherDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(WeatherDto weatherDto) {

        if(checkNull(weatherDto)){
            return;
        }

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

        if(checkNull(weatherDto)){
            return;
        }

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

    /**
     * Проверка null значений в переданном объекте
     * @param weather - проверяемый объект
     * @return true, если найдено null значение, иначе - false
     */
    private boolean checkNull(WeatherDto weather){

        return weather == null || weather.getCurrentObservation() == null
                || weather.getCurrentObservation().getCondition() == null
                || weather.getCurrentObservation().getAtmosphere() == null
                || weather.getCurrentObservation().getAstronomy() == null
                || weather.getCurrentObservation().getWind() == null
                || weather.getForecasts() == null;
    }
}
