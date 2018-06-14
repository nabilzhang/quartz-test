package com.example.quartz.demo.runner;

import com.example.quartz.demo.job.HelloJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty("manager")
@Component
public class Manager implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(Manager.class);

    @Autowired
    private Scheduler scheduler;

    public void run(ApplicationArguments args) throws Exception {

        logger.info("manage started");


        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                                .withMisfireHandlingInstructionFireAndProceed()
                )
                .build();

        if (scheduler.checkExists(job.getKey())) {
            scheduler.rescheduleJob(trigger.getKey(), trigger);
        } else {
            scheduler.scheduleJob(job, trigger);
        }

        Thread.sleep(1000 * 60);

    }
}
