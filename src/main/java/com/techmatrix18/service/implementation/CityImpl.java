package com.techmatrix18.service.implementation;

import com.techmatrix18.model.City;
import com.techmatrix18.repository.CityRepository;
import com.techmatrix18.service.InterfaceCity;
import com.techmatrix18.service.InterfacePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link InterfaceCity} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class CityImpl implements InterfaceCity {
    @Lazy
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        List<City> list = new ArrayList<>();
        cityRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.getById(id);
    }

    @Override
    public boolean addCity(City city) {
        City c = cityRepository.save(city);
        if (!c.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCity(City city) {
        City c = cityRepository.save(city);
        if (!c.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCity(Long id) {
        City city = cityRepository.getById(id);
        if (!city.getTitle().isEmpty()) {
            cityRepository.delete(city);
            return true;
        } else {
            return false;
        }
    }
}

