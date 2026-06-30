package com.kumar.spring_ai.tools.weather;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final ChatClient chatClient;
    private final WeatherTool weatherTool;

    public WeatherController(ChatClient.Builder builder, WeatherTool weatherTool) {
        this.chatClient = builder.build();
        this.weatherTool = weatherTool;
    }

    @GetMapping("/weather")
    public String ask(@RequestParam String question) {
        return chatClient.prompt()
                .user(question)
                .tools(weatherTool)
                .call()
                .content();
    }

    //http://localhost:8081/weather?question=patna?
}
