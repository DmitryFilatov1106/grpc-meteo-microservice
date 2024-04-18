package ru.fildv.datastoregrpcmicroservice.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.fildv.datastoregrpcmicroservice.model.exception.IndicatorNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IndicatorNotFoundException.class)
    public String indicatorNotFound(IndicatorNotFoundException e) {
        return "Indicator not found.";
    }

    @ExceptionHandler
    public String serverException(Exception e) {
        e.printStackTrace();
        return "Bad thing happened for server.";
    }
}
