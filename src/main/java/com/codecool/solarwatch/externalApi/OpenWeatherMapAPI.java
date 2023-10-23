package com.codecool.solarwatch.externalApi;

import com.codecool.solarwatch.model.auxiliary.Coordinates;
import com.codecool.solarwatch.model.geocoding.City;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class OpenWeatherMapAPI implements GeocodingAPI {
  private final String apiKey;
  private final int limit;
  private final RestTemplate restTemplate;
  private final Logger logger;
  
  @Autowired
  public OpenWeatherMapAPI(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    Dotenv dotenv = Dotenv.load();
    apiKey = dotenv.get("OWM_API_KEY");
    limit = 1;
    logger = LoggerFactory.getLogger(OpenWeatherMapAPI.class);
  }
  
  @Override
  public Optional<Coordinates> getCoordinates(String city) {
    try {
      String url = generateURL(city);
      City[] cities = getOpenWeatherCity(url);
      return Optional.of(getCoordinates(cities));
    } catch (Exception e) {
      logger.info(e.getMessage());
      return Optional.empty();
    }
  }
  
  private Coordinates getCoordinates(City[] cities) {
    return new Coordinates(cities[0].lat(), cities[0].lon());
  }
  
  private City[] getOpenWeatherCity(String url) {
    return restTemplate.getForObject(url, City[].class);
  }
  
  private String generateURL(String city) {
    return String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=%d&appid=%s", city, limit, apiKey);
  }
}
