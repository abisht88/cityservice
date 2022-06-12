package com.task.cityservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String city;
    private String country;
    @Transient
    private Integer population;

    public City() {
    }

    public City(String cityName, String country, Integer population) {
        this.city = cityName;
        this.country = country;
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", population=" + population +
                '}';
    }
}
