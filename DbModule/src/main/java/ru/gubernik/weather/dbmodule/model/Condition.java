package ru.gubernik.weather.dbmodule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import java.util.Objects;

/**
 * Условия
 */
@Entity
@Table(name = "condition")
public class Condition {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Version
    private Integer version;

    /**
     * Описание
     */
    @Column(name = "text", length = 50)
    private String text;

    /**
     * Температура
     */
    @Column(name = "temperature")
    private Integer temperature;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "obs_id")
    private Observation observation;

    public Condition() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public Observation getObservation() {
        return observation;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
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
        return "Condition{" +
                "id=" + id +
                ", version=" + version +
                ", text='" + text + '\'' +
                ", temperature=" + temperature +
                ", observation=" + observation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return Objects.equals(id, condition.id) &&
                Objects.equals(version, condition.version) &&
                Objects.equals(text, condition.text) &&
                Objects.equals(temperature, condition.temperature) &&
                Objects.equals(observation, condition.observation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, text, temperature, observation);
    }
}
