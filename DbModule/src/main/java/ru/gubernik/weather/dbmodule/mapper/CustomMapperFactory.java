package ru.gubernik.weather.dbmodule.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;

import javax.enterprise.context.ApplicationScoped;

/**
 * Кастомный mapperFactory
 */
@ApplicationScoped
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    @Override
    public MapperFactory getObject() throws Exception {
        return new DefaultMapperFactory.Builder()
                .mapNulls(false)
                .constructorResolverStrategy(null)
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
