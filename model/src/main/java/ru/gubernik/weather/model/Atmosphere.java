package ru.gubernik.weather.model;

import java.io.Serializable;

/**
 * Атмосфера
 */
public class Atmosphere implements Serializable {

    /**
     * Влажность
     */
    private Integer humidity;

    /**
     * Видимость
     */
    private Integer visibility;

    /**
     * Давление
     */
    private Double pressure;

    private Integer rising;

    public Atmosphere() {
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getRising() {
        return rising;
    }

    public void setRising(Integer rising) {
        this.rising = rising;
    }

    @Override
    public String toString() {
        return "Atmosphere{" +
                "humidity=" + humidity +
                ", visibility=" + visibility +
                ", pressure=" + pressure +
                ", rising=" + rising +
                '}';
    }
}
