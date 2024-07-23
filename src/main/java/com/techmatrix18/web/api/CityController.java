package com.techmatrix18.web.api;

import com.techmatrix18.model.City;
import com.techmatrix18.repository.CityRepository;
import com.techmatrix18.service.CityService;
import com.techmatrix18.service.InvoiceService;
import com.techmatrix18.service.implementation.CityServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities-request")
    public String byGetQueryString(HttpServletRequest request) {
        return request.getQueryString();
    }

    @GetMapping(path = "/list")
    public List<City> getCities() throws ValidationException {
        return cityService.getAllCities();
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public @ResponseBody String addCity (@RequestParam String title, @RequestParam String description) {
        City c = new City();
        c.setTitle(title);
        c.setDescription(description);
        cityService.addCity(c);
        return "{\"status\": \"success\", \"message\": \"City saved successfully!\"}";
    }

    @PatchMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public @ResponseBody String updateBarco (@RequestParam Long cityId, @RequestParam String title, @RequestParam String description) {
        City c = cityService.getCityById(cityId);
        if (c.getId() != null) {
            c.setTitle(title);
            c.setDescription(description);
            cityService.updateCity(c);
        }
        //return "Updated";
        return "{\"status\": \"success\", \"message\": \"City updated successfully!\"}";
    }

    @DeleteMapping(path = "/delete/{cityId}", produces = "application/json;charset=UTF-8")
    public @ResponseBody String deleteCity (@PathVariable Long cityId) {
        City c = cityService.getCityById(cityId);
        if (c.getId() != null) {
            cityService.deleteCity(cityId);
        }
        //return "Deleted";
        return "{\"status\": \"success\", \"message\": \"City deleted successfully!\"}";
    }
}

