package com.spongi.io.surveygenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.spongi.io.surveygenerator.repository")
public class SurveyGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SurveyGeneratorApplication.class, args);
    }
}
