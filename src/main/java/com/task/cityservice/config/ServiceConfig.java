package com.task.cityservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app.services")
public class ServiceConfig {

    private Map<String, Service> dictionary;

    public Service get(String name) {
        return dictionary.getOrDefault(name, null);
    }

    @Data
    public static class Service {
        private String url;
        private String user;
        private String password;
    }
}
