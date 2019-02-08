package ru.gubernik.weather.model;

/**
 * Атмосфера
 */
public class Atmosphere {

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
    private Double presure;

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

    public Double getPresure() {
        return presure;
    }

    public void setPresure(Double presure) {
        this.presure = presure;
    }
}
