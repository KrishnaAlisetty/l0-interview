package com.portal.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class InterviewPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewPortalApplication.class, args);
    }
}
