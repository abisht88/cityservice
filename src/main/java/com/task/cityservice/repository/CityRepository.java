package com.task.cityservice.repository;

import com.task.cityservice.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

    @Query("select country from City where city = :cityName")
    String findCountryByCity(@Param("cityName") String cityName);
}
