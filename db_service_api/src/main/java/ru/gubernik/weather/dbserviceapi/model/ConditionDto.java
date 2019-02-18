package ru.gubernik.weather.dbserviceapi.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Условия
 */
public class ConditionDto implements Serializable {

    /**
     * Описание
     */
    private String text;

    /**
     * Температура
     */
    private Integer temperature;

    public ConditionDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "ConditionDto{" +
                "text='" + text + '\'' +
                ", temperature=" + temperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConditionDto that = (ConditionDto) o;
        return Objects.equals(text, that.text) &&
                Objects.equals(temperature, that.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, temperature);
    }
}
