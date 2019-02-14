package ru.gubernik.weather.dbserviceapi.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Атмосфера
 */
public class AtmosphereDto implements Serializable {

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

    public AtmosphereDto() {
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
        return "AtmosphereDto{" +
                "humidity=" + humidity +
                ", visibility=" + visibility +
                ", pressure=" + pressure +
                ", rising=" + rising +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtmosphereDto that = (AtmosphereDto) o;
        return Objects.equals(humidity, that.humidity) &&
                Objects.equals(visibility, that.visibility) &&
                Objects.equals(pressure, that.pressure) &&
                Objects.equals(rising, that.rising);
    }

    @Override
    public int hashCode() {
        return Objects.hash(humidity, visibility, pressure, rising);
    }
}
