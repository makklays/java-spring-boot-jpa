package com.techmatrix18.web.api;

import com.techmatrix18.model.City;
import com.techmatrix18.model.Invoice;
import com.techmatrix18.service.CityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/all", produces = "application/json;charset=UTF-8")
    public List<City> getCities() throws ValidationException {
        return cityService.getAllCities();
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public Object getOne(@PathVariable String id) throws ValidationException {
        Long cityId = Long.parseLong(id);
        Optional<City> city = cityService.getCityById(cityId);
        if (city.isPresent()) {
            return city.get();
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find city with ID=" + id + "\"}";
        }
    }

    @GetMapping(path = "/page", produces = "application/json;charset=UTF-8")
    public Object getPage(@RequestParam String pageNo, @RequestParam String pageSize) {
        int pNo = Integer.parseInt(pageNo);
        int pSize = Integer.parseInt(pageSize);

        Page<City> cities = cityService.findPaginated(pNo, pSize);
        if (cities != null) {
            return cities;
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find cities with pageNo=" + pageNo + " and pageSize=" + pageSize + " \"}";
        }
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
        Optional<City> c = cityService.getCityById(cityId);
        if (c.isPresent()) {
            c.get().setTitle(title);
            c.get().setDescription(description);
            cityService.updateCity(c.get());
            return "{\"status\": \"success\", \"message\": \"City updated successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find city with ID=" + cityId + "\"}";
        }
    }

    @DeleteMapping(path = "/delete/{cityId}", produces = "application/json;charset=UTF-8")
    public @ResponseBody String deleteCity (@PathVariable Long cityId) {
        Optional<City> c = cityService.getCityById(cityId);
        if (c.isPresent()) {
            cityService.deleteCity(cityId);
            return "{\"status\": \"success\", \"message\": \"City deleted successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find city with ID=" + cityId + "\"}";
        }
    }
}

