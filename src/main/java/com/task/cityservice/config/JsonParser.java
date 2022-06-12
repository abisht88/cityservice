package com.task.cityservice.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.cityservice.exception.JsonParserException;
import com.task.cityservice.model.City;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

@Log
@Component
public class JsonParser {
    @Autowired
    ObjectMapper objectMapper;

    public List<City> parseJsonData(String json) {
        Reader reader = new StringReader(json);
        try {
            log.info("parsing json data");
            return objectMapper.readValue(reader, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new JsonParserException("error parsing json data");
    }

    public String convertToJson(City city) {
        try {
            log.info("converting city data to json");
            return objectMapper.writeValueAsString(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new JsonParserException("error converting to json");
    }
}
