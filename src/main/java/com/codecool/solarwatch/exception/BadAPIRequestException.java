package com.codecool.solarwatch.exception;

public class BadAPIRequestException extends RuntimeException {
  public BadAPIRequestException() {
    super("Bad API Request");
  }
}
