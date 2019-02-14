package ru.gubernik.weather.dbserviceapi;

import java.io.Serializable;
import java.util.Objects;

/**
 * Прогноз
 */
public class ForecastDto implements Serializable {

    /**
     * День недели
     */
    private String day;

    /**
     * Дата
     */
    private Long date;

    /**
     * Макс. температура
     */
    private Integer low;

    /**
     * Мин. температура
     */
    private Integer high;

    /**
     * Описание
     */
    private String text;

    /**
     * Код
     */
    private Integer code;

    public ForecastDto() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ForecastDto{" +
                "day='" + day + '\'' +
                ", date=" + date +
                ", low=" + low +
                ", high=" + high +
                ", text='" + text + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastDto forecast = (ForecastDto) o;
        return Objects.equals(day, forecast.day) &&
                Objects.equals(date, forecast.date) &&
                Objects.equals(low, forecast.low) &&
                Objects.equals(high, forecast.high) &&
                Objects.equals(text, forecast.text) &&
                Objects.equals(code, forecast.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, date, low, high, text, code);
    }
}
