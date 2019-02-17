package ru.gubernik.weather.weatherservice.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.FactoryBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class MapperFacadeImpl implements MapperFacade {

    @Inject
    private MapperFactory mapperFactory;

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

    @Override
    public <L, T> List<T> mapAsList(Iterable<L> list, Class<T> target) {
        try {
            return mapperFactory.getMapperFacade().mapAsList(list, target);
        } catch (Exception e) {
            throw new RuntimeException("mapper error", e);
        }
    }
}