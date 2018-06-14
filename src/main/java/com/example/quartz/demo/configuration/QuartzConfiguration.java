package com.example.quartz.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableScheduling
public class QuartzConfiguration {

    @Autowired
    DataSource dataSource;

    @Bean
    public Properties manageProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(new ClassPathResource("/quartz-manager.properties").getInputStream());
        return prop;
    }

    @Bean
    public Properties wokerProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(new ClassPathResource("/quartz-worker.properties").getInputStream());
        return prop;
    }

    @Bean(name = "scheduler")
    @ConditionalOnProperty("worker")
    public SchedulerFactoryBean schedulerFactory() throws IOException {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setOverwriteExistingJobs(true);
        bean.setQuartzProperties(wokerProperties());
        bean.setDataSource(dataSource);
        return bean;
    }

    @Bean(name = "scheduler")
    @ConditionalOnProperty("manager")
    public SchedulerFactoryBean managerSchedulerFactory() throws IOException {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setOverwriteExistingJobs(true);
        bean.setQuartzProperties(manageProperties());
        bean.setDataSource(dataSource);
        return bean;
    }


}
