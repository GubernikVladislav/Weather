package ru.gubernik.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Модель города
 */
public class Location {

    /**
     * Идентификатор
     */
    @JsonProperty("woeid")
    private Integer woeId;

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

    public Integer getWoeId() {
        return woeId;
    }

    public void setWoeId(Integer woeId) {
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
}
