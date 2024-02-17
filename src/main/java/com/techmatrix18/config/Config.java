package com.techmatrix18.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${rapidapi.val1}")
    private String rapidApiVal1;
    @Value("${rapidapi.val2}")
    private String rapidApiVal2;

    public String getApplicationName() {
        return applicationName;
    }

    public String getRapidApiVal1() {
        return rapidApiVal1;
    }

    public String getRapidApiVal2() {
        return rapidApiVal2;
    }

    @Override
    public String toString() {
        return "Config{" +
                "applicationName='" + applicationName + '\'' +
                ", rapidApiVal1='" + rapidApiVal1 + '\'' +
                ", rapidApiVal2='" + rapidApiVal2 + '\'' +
                '}';
    }
}

