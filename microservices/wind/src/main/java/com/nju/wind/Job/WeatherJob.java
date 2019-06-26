package com.nju.wind.Job;

import com.nju.wind.service.WeatherService;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class WeatherJob implements Job {
    @Autowired
    private WeatherService weatherService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        weatherService.produceWeather();
        log.info("produceWeather");
    }
}
