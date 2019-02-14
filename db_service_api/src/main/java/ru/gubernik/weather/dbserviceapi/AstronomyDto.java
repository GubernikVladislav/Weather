package ru.gubernik.weather.dbserviceapi;

import java.io.Serializable;
import java.util.Objects;

/**
 * Астрономия
 */
public class AstronomyDto implements Serializable {

    /**
     * Рассвет
     */
    private String sunrise;

    /**
     * Закат
     */
    private String sunset;

    public AstronomyDto() {
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
        AstronomyDto astronomyDto = (AstronomyDto) o;
        return Objects.equals(sunrise, astronomyDto.sunrise) &&
                Objects.equals(sunset, astronomyDto.sunset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sunrise, sunset);
    }
}
