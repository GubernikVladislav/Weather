package ru.gubernik.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

/**
 * Модель города
 */
@Entity
@Table(name = "LOCATION")
public class Location implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Version
    private Integer version;

    /**
     * Идентификатор
     */
    @JsonProperty("woeid")
    @Column(name = "woeid")
    private Long woeId;

    /**
     * Название города
     */
    @Column(name = "city")
    private String city;

    /**
     * Регион
     */
    @Column(name = "region")
    private String region;

    /**
     * Страна
     */
    @Column(name = "country")
    private String country;

    /**
     * Широта
     */
    @Column(name = "lat")
    private Double lat;

    /**
     * Долгота
     */
    @JsonProperty("long")
    @Column(name = "long")
    private Double lon;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "weather_id")
    private Weather weather;

    /**
     * Часовой пояс
     */
    @JsonProperty("timezone_id")
    @Column(name = "timezone_id")
    private String timezoneId;

    public Location() {
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
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

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Location{" +
                "woeId=" + woeId +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", lat=" + lat +
                ", $long=" + lon +
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
                Objects.equals(lon, location.lon) &&
                Objects.equals(timezoneId, location.timezoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(woeId, city, region, country, lat, lon, timezoneId);
    }
}
