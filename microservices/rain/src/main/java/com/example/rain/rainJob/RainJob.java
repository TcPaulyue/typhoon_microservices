package com.example.rain.rainJob;

import com.example.rain.service.RainService;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class RainJob implements Job{

    @Autowired
    private RainService rainService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        rainService.produceRain();
        log.info("produceRain");
    }

}
