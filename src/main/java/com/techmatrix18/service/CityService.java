package com.techmatrix18.service;

import com.techmatrix18.model.City;
import com.techmatrix18.model.Invoice;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link com.techmatrix18.model.City}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface CityService {
    List<City> getAllCities();
    Optional<City> getCityById(Long id);

    boolean addCity(City city);
    boolean updateCity(City city);
    boolean deleteCity(Long id);

    Page<City> findPaginated(int pageNo, int pageSize);
}

