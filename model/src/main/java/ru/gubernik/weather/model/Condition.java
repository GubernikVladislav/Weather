package ru.gubernik.weather.model;

import java.io.Serializable;

/**
 * Условия
 */
public class Condition implements Serializable {

    /**
     * Описание
     */
    private String text;

    /**
     * Код
     */
    private Integer code;

    /**
     * Температура
     */
    private Integer temperature;

    public Condition() {
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

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
}
