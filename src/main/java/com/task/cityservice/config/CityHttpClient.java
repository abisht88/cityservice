package com.task.cityservice.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CityHttpClient {

    @Autowired
    ServiceConfig service;

    @Bean
    public CloseableHttpClient getHttpClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        ServiceConfig.Service cityService = service.get("city");
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(cityService.getUser(), cityService.getPassword()));
        return HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
    }
}
