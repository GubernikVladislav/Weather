package ru.gubernik.weather.model;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Wind{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wind wind = (Wind) o;
        return Objects.equals(chill, wind.chill) &&
                Objects.equals(direction, wind.direction) &&
                Objects.equals(speed, wind.speed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chill, direction, speed);
    }
}
