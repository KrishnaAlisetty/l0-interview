package com.portal.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAsync
public class InterviewPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewPortalApplication.class, args);
    }
}
