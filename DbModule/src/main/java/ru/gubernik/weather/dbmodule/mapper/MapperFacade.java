package ru.gubernik.weather.dbmodule.mapper;

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

    /**
     * Изменение полей объекта в соответствии с переданным объектом
     * @param object - объект с обновленными полями
     * @param target - обновляемый объект
     *
     */
    <O, T> void map(O object, T target);
}
