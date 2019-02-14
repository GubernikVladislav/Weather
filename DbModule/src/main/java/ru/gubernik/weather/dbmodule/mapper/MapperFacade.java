package ru.gubernik.weather.dbmodule.mapper;

public interface MapperFacade {

    <O, T> T map(O object, Class<T> target);

    <O, T> void map(O object, T target);
}
