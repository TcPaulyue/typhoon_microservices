package com.nju.typhoon.typhoonJob;

import com.nju.typhoon.service.TyphoonService;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class TyphoonJob implements Job{

    @Autowired
    private TyphoonService typhoonService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        typhoonService.produceTyphoon();
        log.info("produceTyphoon");
    }
}
