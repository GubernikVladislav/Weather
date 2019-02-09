package ru.gubernik.weather.model;

import java.io.Serializable;

/**
 * Текущая погода
 */
public class Observation implements Serializable {

    /**
     * Ветер
     */
    private Wind wind;

    /**
     * Атмосфера
     */
    private Atmosphere atmosphere;

    /**
     * Астрономия
     */
    private Astronomy astronomy;

    /**
     * Погодные условия
     */
    private Condition condition;

    /**
     * Время наблюдения
     */
    private Long pubDate;

    public Observation() {
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Long getPubDate() {
        return pubDate;
    }

    public void setPubDate(Long pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", condition=" + condition +
                ", pubDate=" + pubDate +
                '}';
    }
}
