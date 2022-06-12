package com.task.cityservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.cityservice.model.City;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Ignore;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class JsonParserTest {
    @Mock
    ObjectMapper mapper;

    String json;
    List<City> list = new ArrayList<>();

    @Before
    public void setUP() {
        MockitoAnnotations.openMocks(this);
        json = "[\n" +
                "    {\n" +
                "        \"city\": \"Berlin\",\n" +
                "        \"population\": 3645000\n" +
                "    },\n" +
                "    {\n" +
                "        \"city\": \"London\",\n" +
                "        \"population\": 9000000\n" +
                "    },\n" +
                "    {\n" +
                "        \"city\": \"Virginia\",\n" +
                "        \"population\": 7000000\n" +
                "    },\n" +
                "    {\n" +
                "        \"city\": \"Warsaw\",\n" +
                "        \"population\": 1765000\n" +
                "    },\n" +
                "    {\n" +
                "        \"city\": \"Pune\",\n" +
                "        \"population\": 4000000\n" +
                "    }\n" +
                "]";
        list.add(new City("Berlin", "Germany", 3645000));
        list.add(new City("London", "UK", 9000000));
        list.add(new City("Virginia", "USA", 7000000));
        list.add(new City("Warsaw", "Poland", 1765000));
        list.add(new City("Pune", "India", 4000000));
    }

    @Ignore	
    @Test
    public void willConvertToJson() throws JsonProcessingException {
        City city = new City("London", "UK", 9000000);
        String json = "{\"city\":\"London\",\"country\":\"UK\",\"population\":9000000}";
        when(mapper.writeValueAsString(any())).thenReturn(json);
        assertEquals(json, new JsonParser().convertToJson(city));
    }
    
   @Ignore 
    @Test
    public void willParseCityData() throws IOException {
        List<City> list = new JsonParser().parseJsonData(json);
        when(mapper.readValue(any(Reader.class), any(TypeReference.class))).thenReturn(list);
        assertEquals(5, list.size());
        assertEquals("Berlin", list.get(0).getCity());
        assertEquals("Pune", list.get(4).getCity());
    }

}