package com.codecool.solarwatch.service;

import com.codecool.solarwatch.exception.BadAPIRequestException;
import com.codecool.solarwatch.externalApi.GeocodingAPI;
import com.codecool.solarwatch.externalApi.SunTimeAPI;
import com.codecool.solarwatch.model.auxiliary.Coordinates;
import com.codecool.solarwatch.model.weather.DailySunTimeDetail;
import com.codecool.solarwatch.model.weather.DailyWeatherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DailyWeatherService {
  private final Logger logger;
  private final GeocodingAPI geocodingAPI;
  private final SunTimeAPI sunTimeAPI;
  
  @Autowired
  public DailyWeatherService(GeocodingAPI geocodingAPI, SunTimeAPI sunTimeAPI) {
    this.geocodingAPI = geocodingAPI;
    this.sunTimeAPI = sunTimeAPI;
    logger = LoggerFactory.getLogger(DailyWeatherService.class);
  }
  
  public DailyWeatherDTO getDailyWeatherReport(String city, LocalDate date) {
    if (date == null) {
      date = LocalDate.now();
    }
    Optional<Coordinates> coordinates = geocodingAPI.getCoordinates(city);
    if (coordinates.isEmpty()) {
      throw new BadAPIRequestException();
    }
    Optional<DailySunTimeDetail> sunTimeDetail = sunTimeAPI.getSunTime(coordinates.get(), date);
    if (sunTimeDetail.isEmpty()) {
      throw new BadAPIRequestException();
    }
    return getDailyWeatherDTO(city, date, sunTimeDetail.get());
  }
  
  private DailyWeatherDTO getDailyWeatherDTO(String city, LocalDate date, DailySunTimeDetail sunTimeDetail) {
    return new DailyWeatherDTO(city, date, sunTimeDetail.sunrise(), sunTimeDetail.sunset());
  }
}
