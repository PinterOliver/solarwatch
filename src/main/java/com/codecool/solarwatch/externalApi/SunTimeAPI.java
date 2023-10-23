package com.codecool.solarwatch.externalApi;

import com.codecool.solarwatch.model.auxiliary.Coordinates;
import com.codecool.solarwatch.model.weather.DailySunTimeDetail;

import java.time.LocalDate;
import java.util.Optional;

public interface SunTimeAPI {
  Optional<DailySunTimeDetail> getSunTime(Coordinates coordinates, LocalDate date);
}
