package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.exception.BadAPIRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WeatherControllerAdvice {
  @ExceptionHandler (BadAPIRequestException.class)
  public ResponseEntity<Exception> handleBadAPIRequestException(BadAPIRequestException exception) {
    return ResponseEntity.badRequest().body(exception);
  }
}
