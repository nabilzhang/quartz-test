package com.example.quartz.demo.runner;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty("worker")
@Component
public class Worker implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    @Autowired
    private Scheduler scheduler;

    public void run(ApplicationArguments args) throws Exception {
        logger.info("worker started");
    }
}
