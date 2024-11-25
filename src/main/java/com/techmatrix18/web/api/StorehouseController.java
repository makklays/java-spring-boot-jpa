package com.techmatrix18.web.api;

import com.techmatrix18.model.Storehouse;
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

    public StorehouseController(StorehouseService storehouseService) {
        this.storehouseService = storehouseService;
    }

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    public List<Storehouse> getStorehouses() throws ValidationException {
        return storehouseService.getAllStorehouses();
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Storehouse> addStorehouse (@Valid @RequestBody Storehouse storehouse) {
       if (storehouse.getId() != null) {
           return ResponseEntity.badRequest().build();
       }
        storehouseService.addStorehouse(storehouse);
        return ResponseEntity.ok(storehouse);
    }

    @PatchMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public @ResponseBody String updateStorehouse (@RequestParam Long storehouseId, @RequestParam Long cityId, @RequestParam String title, @RequestParam String description) {
        Storehouse s = storehouseService.getStorehouseById(storehouseId);
        if (s.getId() != null) {
          //  s.setCityId(cityId);
            s.setTitle(title);
            s.setDescription(description);
            storehouseService.updateStorehouse(s);
        }
        return "{\"status\": \"success\", \"message\": \"City updated successfully!\"}";
    }

    @DeleteMapping(path = "/delete/{storehouseId}", produces = "application/json;charset=UTF-8")
    public @ResponseBody String deleteStorehouse (@PathVariable Long storehouseId) {
        Storehouse s = storehouseService.getStorehouseById(storehouseId);
        if (s.getId() != null) {
            storehouseService.deleteStorehouse(storehouseId);
        }
        return "{\"status\": \"success\", \"message\": \"City deleted successfully!\"}";
    }
}

