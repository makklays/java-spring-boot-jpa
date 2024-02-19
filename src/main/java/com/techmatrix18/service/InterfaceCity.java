package com.techmatrix18.service;

import com.techmatrix18.model.City;
import java.util.List;

public interface InterfaceCity {
    List<City> getAllCities();
    City getCityById(Long id);

    boolean addCity(City city);
    boolean updateCity(City city);
    boolean deleteCity(Long id);
}

