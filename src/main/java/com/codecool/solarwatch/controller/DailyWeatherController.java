package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.weather.DailyWeatherDTO;
import com.codecool.solarwatch.service.DailyWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping
public class DailyWeatherController {
  DailyWeatherService dailyWeatherService;
  
  @Autowired
  public DailyWeatherController(DailyWeatherService dailyWeatherService) {
    this.dailyWeatherService = dailyWeatherService;
  }
  
  @GetMapping ("/sun")
  public ResponseEntity<DailyWeatherDTO> getDailyWeather(@RequestParam String city,
          @RequestParam (required = false) LocalDate date) {
    return ResponseEntity.ok().body(dailyWeatherService.getDailyWeatherReport(city, date));
  }
}
