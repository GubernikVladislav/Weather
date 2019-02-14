package ru.gubernik.weather.dbmodule.mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class MapperFacadeImpl implements MapperFacade {

    @Inject
    private CustomMapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public <O, T> T map(O object, Class<T> target) {
        try {
            return mapperFactory.getObject().getMapperFacade().map(object, target);
        } catch (Exception e) {
            throw new RuntimeException("Mapper error", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O, T> void map(O object, T target) {

    }
}
