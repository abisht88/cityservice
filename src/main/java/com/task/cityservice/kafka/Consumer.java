package com.task.cityservice.kafka;

import com.task.cityservice.model.City;
import com.task.cityservice.service.CityService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log
@Service
public class Consumer {

    @Autowired
    CityService service;

    @KafkaListener(topics = "input_topic", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(City userData) throws IOException {
        log.info(String.format("Consumed message : %s", userData.getCity()));
        service.processData(userData.getCity().toUpperCase());
    }
}