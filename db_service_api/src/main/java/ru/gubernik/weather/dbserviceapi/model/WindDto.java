package ru.gubernik.weather.dbserviceapi.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Ветер
 */
public class WindDto implements Serializable {

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

    public WindDto() {
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
        return "WindDto{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WindDto wind = (WindDto) o;
        return Objects.equals(chill, wind.chill) &&
                Objects.equals(direction, wind.direction) &&
                Objects.equals(speed, wind.speed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chill, direction, speed);
    }
}
