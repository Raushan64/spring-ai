package com.kumar.spring_ai.tools.weather;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherTool {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();

    @Tool(description = "Get current weather by city")
    public String getWeather(String city) {
        return restClient.get()
                .uri("https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city)
                .retrieve()
                .body(String.class);
    }
}