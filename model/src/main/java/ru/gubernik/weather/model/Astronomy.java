package ru.gubernik.weather.model;

import java.io.Serializable;

/**
 * Астрономия
 */
public class Astronomy implements Serializable {

    /**
     * Рассвет
     */
    private String sunrise;

    /**
     * Закат
     */
    private String sunset;

    public Astronomy() {
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
