package com.codecool.solarwatch.model.weather;

import java.time.LocalDate;
import java.time.LocalTime;

public record DailyWeatherDTO(String city, LocalDate date, LocalTime sunrise, LocalTime sunset) {
}
