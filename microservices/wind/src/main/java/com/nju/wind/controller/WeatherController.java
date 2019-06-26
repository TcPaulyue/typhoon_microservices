package com.nju.wind.controller;

import com.nju.wind.domain.Weather;
import com.nju.wind.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value="/weather")
    public Weather getWeather(){
        return weatherService.getWeather();
    }
}
