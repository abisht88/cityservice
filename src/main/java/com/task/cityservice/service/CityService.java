package com.task.cityservice.service;

import com.task.cityservice.model.City;

import java.io.IOException;

public interface CityService {

    String getCountry(String cityName);

    Integer getCityPopulation(String cityName) throws IOException;

    void processData(String cityName) throws IOException;
}