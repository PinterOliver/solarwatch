package com.codecool.solarwatch.externalApi;

import com.codecool.solarwatch.model.auxiliary.Coordinates;
import com.codecool.solarwatch.model.weather.SunTime;
import com.codecool.solarwatch.model.weather.SunTimeDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class SunriseSunsetAPI implements SunTimeAPI {
  private final Logger logger;
  private final RestTemplate restTemplate;
  
  @Autowired
  public SunriseSunsetAPI(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    logger = LoggerFactory.getLogger(SunriseSunsetAPI.class);
  }
  
  @Override
  public Optional<SunTimeDetail> getSunTime(Coordinates coordinates, LocalDate date) {
    try {
      String url = generateUrl(coordinates, date);
      SunTime sunTime = getSunriseSunsetInfo(url);
      return Optional.of(sunTime.results());
    } catch (Exception e) {
      logger.info(e.getMessage());
      return Optional.empty();
    }
  }
  
  private String generateUrl(Coordinates coordinates, LocalDate date) {
    return String.format("https://api.sunrise-sunset.org/json?lat=%f&lng=%f&date=%s",
                         coordinates.latitude(),
                         coordinates.longitude(),
                         date.format(DateTimeFormatter.ISO_LOCAL_DATE));
  }
  
  private SunTime getSunriseSunsetInfo(String url) {
    return restTemplate.getForObject(url, SunTime.class);
  }
}
