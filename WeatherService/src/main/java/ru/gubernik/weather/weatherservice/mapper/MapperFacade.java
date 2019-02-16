package ru.gubernik.weather.weatherservice.mapper;

import java.util.List;

/**
 * Маппер для преобразования классов
 */
public interface MapperFacade {

    /**
     * Преобразование объекта к указанному классу
     * @param object - преобразуемый объект
     * @param target - целевой класс преобразования
     * @return
     */
    <O, T> T map(O object, Class<T> target);


    <L, T> List<T> mapAsList(Iterable<L> list, Class<T> target);

}
