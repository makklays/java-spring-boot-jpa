package com.techmatrix18.web.api;

import com.techmatrix18.model.City;
import com.techmatrix18.model.Storehouse;
import com.techmatrix18.service.CityService;
import com.techmatrix18.service.StorehouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

/**
 * Simple controller for Storehouse
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 19-02-2024
 */

@RestController
@RequestMapping("/api/v1/storehouses")
public class StorehouseController {

    private final StorehouseService storehouseService;
    private final CityService cityService;

    public StorehouseController(StorehouseService storehouseService, CityService cityService) {
        this.storehouseService = storehouseService;
        this.cityService = cityService;
    }

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all", produces = "application/json;charset=UTF-8")
    public List<Storehouse> getStorehouses() throws ValidationException {
        return storehouseService.getAllStorehouses();
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public Object getOneStorehouse(@PathVariable String id) throws ValidationException {
        Long storehouseId = Long.parseLong(id);
        Optional<Storehouse> st = storehouseService.getStorehouseById(storehouseId);
        if (st.isPresent()) {
            return st.get();
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find storehouse with ID=" + id + "\"}";
        }
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public @ResponseBody String addStorehouse(@RequestParam Long cityId, @RequestParam String title, @RequestParam String description) {
        /*if (storehouse.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        storehouseService.addStorehouse(storehouse);
        return ResponseEntity.ok(storehouse);*/

        Optional<City> city = cityService.getCityById(cityId);
        Storehouse st = new Storehouse();
        st.setTitle(title);
        st.setDescription(description);
        if (city.isPresent()) {
            st.setCity(city.get());
        }
        if (storehouseService.addStorehouse(st)) {
            return "{\"status\": \"success\", \"message\": \"Storehouse added successfully!\"}";
            // return ResponseEntity.ok(storehouse);
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't add storehouse\"}";
            // return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public @ResponseBody String updateStorehouse (@RequestParam Long storehouseId, @RequestParam Long cityId, @RequestParam String title, @RequestParam String description) {
        Optional<Storehouse> s = storehouseService.getStorehouseById(storehouseId);
        if (s.isPresent()) {
            Optional<City> city = cityService.getCityById(cityId);
            if (city.isPresent()) {
                s.get().setCity(city.get());
            }
            s.get().setTitle(title);
            s.get().setDescription(description);
            if (storehouseService.updateStorehouse(s.get())) {
                return "{\"status\": \"success\", \"message\": \"Storehouse updated successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't update storehouse\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find storehouse with ID=" + storehouseId + "\"}";
        }
    }

    @DeleteMapping(path = "/delete/{storehouseId}", produces = "application/json;charset=UTF-8")
    public @ResponseBody String deleteStorehouse (@PathVariable Long storehouseId) {
        Optional<Storehouse> s = storehouseService.getStorehouseById(storehouseId);
        if (s.isPresent()) {
            if (storehouseService.deleteStorehouse(storehouseId)) {
                return "{\"status\": \"success\", \"message\": \"Storehouse deleted successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't delete storehouse\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find storehouse with ID=" + storehouseId + "\"}";
        }
    }
}

