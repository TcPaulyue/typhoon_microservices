package com.example.rain.config;
import com.example.rain.rainJob.RainJob;
import com.example.rain.domain.MyJobFactory;
import lombok.extern.log4j.Log4j2;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


@Configuration
@Log4j2
public class QuartzConfig {
     @Autowired
     private MyJobFactory myJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger updateTrigger) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(myJobFactory);
        log.info("myJobFactory:"+myJobFactory);
        schedulerFactoryBean.setTriggers(updateTrigger);
        return schedulerFactoryBean;
    }


    @Bean(name = "updateJobDetail")
    public JobDetailFactoryBean uploadJobDetail() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(RainJob.class);
        jobDetail.setGroup("Group1");
        return jobDetail;
    }

    @Bean(name = "updateTrigger")
    public CronTriggerFactoryBean updateTriggerFactoryBean(JobDetail updateJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setCronExpression("0/5 * * * * ?");
        trigger.setJobDetail(updateJobDetail);
        return trigger;
    }
}