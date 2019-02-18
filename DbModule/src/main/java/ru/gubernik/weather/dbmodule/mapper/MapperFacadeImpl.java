package ru.gubernik.weather.dbmodule.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.FactoryBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class MapperFacadeImpl implements MapperFacade {

    @Inject
    private FactoryBean<MapperFactory> mapperFactory;

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

        try {
            if(mapperFactory.getObject() != null) {
                mapperFactory.getObject().getMapperFacade().map(object, target);
            }
        } catch (Exception e) {
            throw new RuntimeException("Mapper error", e);
        }
    }
}
