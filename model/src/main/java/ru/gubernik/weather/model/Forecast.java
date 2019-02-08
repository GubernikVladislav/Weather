package ru.gubernik.weather.model;

/**
 * Прогноз
 */
public class Forecast {

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

    public Forecast() {
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
}
