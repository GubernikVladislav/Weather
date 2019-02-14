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
     * Код
     */
    private Integer code;

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

    @Override
    public String toString() {
        return "Condition{" +
                "text='" + text + '\'' +
                ", code=" + code +
                ", temperature=" + temperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConditionDto condition = (ConditionDto) o;
        return Objects.equals(text, condition.text) &&
                Objects.equals(code, condition.code) &&
                Objects.equals(temperature, condition.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, code, temperature);
    }
}
