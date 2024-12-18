package com.techmatrix18.service.impl;

import com.techmatrix18.model.City;
import com.techmatrix18.model.Invoice;
import com.techmatrix18.repository.CityRepository;
import com.techmatrix18.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link CityService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class CityServiceImpl implements CityService {
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
    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
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

    @Override
    public Page<City> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return cityRepository.findAll(pageable);
    }
}

