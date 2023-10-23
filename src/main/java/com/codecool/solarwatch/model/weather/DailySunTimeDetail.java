package com.codecool.solarwatch.model.weather;

import java.time.LocalTime;

public record DailySunTimeDetail(LocalTime sunrise, LocalTime sunset) {
}
