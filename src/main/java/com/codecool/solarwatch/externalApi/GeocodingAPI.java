package com.codecool.solarwatch.externalApi;

import com.codecool.solarwatch.model.auxiliary.Coordinates;

import java.util.Optional;

public interface GeocodingAPI {
  Optional<Coordinates> getCoordinates(String city);
}
