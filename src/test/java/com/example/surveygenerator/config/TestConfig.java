package com.example.surveygenerator.config;

import com.spongi.io.surveygenerator.entity.DbSurvey;
import com.spongi.io.surveygenerator.mapper.SurveyMapper;
import com.spongi.io.surveygenerator.repository.SurveyRepository;
import com.spongi.io.surveygenerator.service.SurveyAdminService;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.spongi.io.surveygenerator.repository")
@EntityScan(basePackageClasses = DbSurvey.class)
@ComponentScan(basePackageClasses = {
        SurveyAdminService.class,
        SurveyRepository.class,
        SurveyMapper.class
})
@ImportAutoConfiguration({
        ValidationAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        FlywayAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class TestConfig {
}
