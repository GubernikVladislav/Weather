package ru.gubernik.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Модель города
 */
public class Location implements Serializable {

    /**
     * Идентификатор
     */
    @JsonProperty("woeid")
    private Long woeId;

    /**
     * Название города
     */
    private String city;

    /**
     * Регион
     */
    private String region;

    /**
     * Страна
     */
    private String country;

    /**
     * Широта
     */
    private Double lat;

    /**
     * Долгота
     */
    @JsonProperty("long")
    private Double $long;

    /**
     * Часовой пояс
     */
    @JsonProperty("timezone_id")
    private String timezoneId;

    public Location() {
    }

    public Long getWoeId() {
        return woeId;
    }

    public void setWoeId(Long woeId) {
        this.woeId = woeId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double get$long() {
        return $long;
    }

    public void set$long(double $long) {
        this.$long = $long;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "woeId=" + woeId +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", lat=" + lat +
                ", $long=" + $long +
                ", timezoneId='" + timezoneId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(woeId, location.woeId) &&
                Objects.equals(city, location.city) &&
                Objects.equals(region, location.region) &&
                Objects.equals(country, location.country) &&
                Objects.equals(lat, location.lat) &&
                Objects.equals($long, location.$long) &&
                Objects.equals(timezoneId, location.timezoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(woeId, city, region, country, lat, $long, timezoneId);
    }
}
