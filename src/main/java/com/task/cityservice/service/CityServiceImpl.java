package com.task.cityservice.service;

import com.task.cityservice.config.CityHttpClient;
import com.task.cityservice.config.JsonParser;
import com.task.cityservice.config.ServiceConfig;
import com.task.cityservice.exception.CityNotFoundException;
import com.task.cityservice.kafka.Producer;
import com.task.cityservice.model.City;
import com.task.cityservice.repository.CityRepository;
import lombok.extern.java.Log;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

@Log
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    Producer producer;
    @Autowired
    private CityRepository repository;
    @Autowired
    CityHttpClient httpClient;
    @Autowired
    ServiceConfig service;
    @Autowired
    JsonParser jsonParser;

    @Override
    public String getCountry(String cityName) {
        return repository.findCountryByCity(cityName);
    }

    @Override
    public Integer getCityPopulation(String cityName) throws IOException {
        HttpGet request = new HttpGet(getURL());
        HttpEntity responseEntity = httpClient.getHttpClient().execute(request).getEntity();
        String response = EntityUtils.toString(responseEntity);
        log.info("inside city service, response from city service " + response);
        Optional<City> city = jsonParser.parseJsonData(response).stream().filter(c -> cityName.equals(c.getCity())).findFirst();
        if (city.isPresent()) {
            return city.get().getPopulation();
        }
        throw new CityNotFoundException(format("City %s not available", cityName));
    }

    @Override
    public void processData(String cityName) throws IOException {
        if (cityName != null && !cityName.isEmpty()) {
            String country = getCountry(cityName);
            Integer population = getCityPopulation(cityName);
            producer.sendMessage(jsonParser.convertToJson(new City(cityName, country, population)));
        } else {
            throw new CityNotFoundException(format("City %s not available", cityName));
        }
    }

    private String getURL() {
        return service.get("city").getUrl();
    }
}
