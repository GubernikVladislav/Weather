package ru.gubernik.weather.weatherservice.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class MapperFacadeImpl implements MapperFacade {

    private final MapperFactory mapperFactory;

    @Autowired
    public MapperFacadeImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O, T> T map(O object, Class<T> target) {
        try {
            return mapperFactory.getMapperFacade().map(object, target);
        } catch (Exception e) {
            throw new RuntimeException("Mapper error", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O, T> void map(O object, T target) {
        try {
            mapperFactory.getMapperFacade().map(object, target);
        } catch (Exception e) {
            throw new RuntimeException("Mapper error", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <L, T> List<T> mapAsList(Iterable<L> list, Class<T> target) {
        try {
            return mapperFactory.getMapperFacade().mapAsList(list, target);
        } catch (Exception e) {
            throw new RuntimeException("mapper error", e);
        }
    }
}
