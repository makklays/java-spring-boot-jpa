package com.techmatrix18.web.api;

import com.techmatrix18.model.City;
import com.techmatrix18.repository.CityRepository;
import com.techmatrix18.service.implementation.CityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Simple controller for City
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityImpl cityService;

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    public List<City> getCities() throws ValidationException {
        return cityService.getAllCities();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addCity (@RequestParam String title, @RequestParam String description) {
        City c = new City();
        c.setTitle(title);
        c.setDescription(description);
        cityService.addCity(c);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateBarco (@RequestParam Long cityId, @RequestParam String title, @RequestParam String description) {
        City c = cityService.getCityById(cityId);
        if (c.getId() != null) {
            c.setTitle(title);
            c.setDescription(description);
            cityService.updateCity(c);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{cityId:\\\\d+}")
    public @ResponseBody String deleteCity (@PathVariable Long cityId) {
        City c = cityService.getCityById(cityId);
        if (c.getId() != null) {
            cityService.deleteCity(cityId);
        }
        return "Deleted";
    }
}

