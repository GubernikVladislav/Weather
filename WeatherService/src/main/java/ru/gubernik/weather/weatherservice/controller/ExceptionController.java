package ru.gubernik.weather.weatherservice.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.gubernik.weather.weatherservice.view.ErrorView;

/**
 * Обработка исключений
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public ErrorView checkError(RuntimeException e){
        return new ErrorView(e.getMessage());
    }
}
