package ru.gubernik.weather.model;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Astronomy{" +
                "sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Astronomy astronomy = (Astronomy) o;
        return Objects.equals(sunrise, astronomy.sunrise) &&
                Objects.equals(sunset, astronomy.sunset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sunrise, sunset);
    }
}
