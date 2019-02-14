package ru.gubernik.weather.dbserviceapi;

import java.io.Serializable;
import java.util.Objects;

/**
 * Текущая погода
 */
public class ObservationDto implements Serializable {

    /**
     * Время наблюдения
     */
    private Long pubDate;

    /**
     * Ветер
     */
    private WindDto wind;

    /**
     * Атмосфера
     */
    private AtmosphereDto atmosphere;

    /**
     * Астрономия
     */
    private AstronomyDto astronomy;

    /**
     * Погодные условия
     */
    private ConditionDto condition;

    public ObservationDto() {
    }

    public WindDto getWind() {
        return wind;
    }

    public void setWind(WindDto wind) {
        this.wind = wind;
    }

    public AtmosphereDto getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(AtmosphereDto atmosphere) {
        this.atmosphere = atmosphere;
    }

    public AstronomyDto getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(AstronomyDto astronomy) {
        this.astronomy = astronomy;
    }

    public ConditionDto getCondition() {
        return condition;
    }

    public void setCondition(ConditionDto condition) {
        this.condition = condition;
    }

    public Long getPubDate() {
        return pubDate;
    }

    public void setPubDate(Long pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "ObservationDto{" +
                "wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", condition=" + condition +
                ", pubDate=" + pubDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObservationDto that = (ObservationDto) o;
        return Objects.equals(pubDate, that.pubDate) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(atmosphere, that.atmosphere) &&
                Objects.equals(astronomy, that.astronomy) &&
                Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pubDate, wind, atmosphere, astronomy, condition);
    }
}
