package ru.gubernik.weather.dbmodule.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class MapperFacadeImpl implements MapperFacade {

    @Autowired
    private MapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public <O, T> T map(O object, Class<T> target) {

        return mapperFactory.getMapperFacade().map(object, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O, T> void map(O object, T target) {

        mapperFactory.getMapperFacade().map(object, target);
    }
}
