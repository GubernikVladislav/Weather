package ru.gubernik.weather.dbmodule.mapper;

import ma.glasnost.orika.MapperFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MapperFacadeImpl implements MapperFacade {

    @Inject
    private CustomMapperFactory mapperFactory;

    @Override
    public <O, T> T map(O object, Class<T> target) {
        try {
            return mapperFactory.getObject().getMapperFacade().map(object, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <O, T> void map(O object, T target) {

    }
}
