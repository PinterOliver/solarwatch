package com.codecool.solarwatch.service;

import com.codecool.solarwatch.exception.BadAPIRequestException;
import com.codecool.solarwatch.externalApi.GeocodingAPI;
import com.codecool.solarwatch.externalApi.SunTimeAPI;
import com.codecool.solarwatch.model.auxiliary.Coordinates;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DailyWeatherServiceTest {
  @Test
  void getDailyWeatherReport_throws_bad_api_exception_when_got_empty_optional_from_geocoding_api() {
    // GIVEN
    GeocodingAPI geocodingAPI = mock(GeocodingAPI.class);
    SunTimeAPI sunTimeAPI = mock(SunTimeAPI.class);
    String city = "city";
    LocalDate date = mock(LocalDate.class);
    when(geocodingAPI.getCoordinates(city)).thenReturn(Optional.empty());
    DailyWeatherService dailyWeatherService = new DailyWeatherService(geocodingAPI, sunTimeAPI);
    
    // WHEN
    
    // THEN
    assertThrows(BadAPIRequestException.class, () -> dailyWeatherService.getDailyWeatherReport(city, date));
  }
  
  @Test
  void getDailyWeatherReport_throws_bad_api_exception_when_got_empty_optional_from_sun_time_api() {
    // GIVEN
    GeocodingAPI geocodingAPI = mock(GeocodingAPI.class);
    SunTimeAPI sunTimeAPI = mock(SunTimeAPI.class);
    String city = "city";
    LocalDate date = mock(LocalDate.class);
    Coordinates coordinates = mock(Coordinates.class);
    when(geocodingAPI.getCoordinates(city)).thenReturn(Optional.of(coordinates));
    when(sunTimeAPI.getSunTime(coordinates, date)).thenReturn(Optional.empty());
    DailyWeatherService dailyWeatherService = new DailyWeatherService(geocodingAPI, sunTimeAPI);
    
    // WHEN
    
    // THEN
    assertThrows(BadAPIRequestException.class, () -> dailyWeatherService.getDailyWeatherReport(city, date));
  }
}
