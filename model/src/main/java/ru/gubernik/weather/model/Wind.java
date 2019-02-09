package ru.gubernik.weather.model;

import java.io.Serializable;

/**
 * Ветер
 */
public class Wind implements Serializable {

    /**
     *
     */
    private Integer chill;

    /**
     * Направление
     */
    private Integer direction;

    /**
     * Скорость
     */
    private Double speed;

    public Wind() {
    }

    public Integer getChill() {
        return chill;
    }

    public void setChill(Integer chill) {
        this.chill = chill;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}
