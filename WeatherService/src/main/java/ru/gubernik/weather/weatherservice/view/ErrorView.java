package ru.gubernik.weather.weatherservice.view;

/**
 * Представление иключения
 */
public class ErrorView {

    /**
     * Текст ошибки
     */
    private String error;

    public ErrorView(String error){
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
