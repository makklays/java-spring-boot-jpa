package com.techmatrix18.web.api;

import com.techmatrix18.model.Storehouse;
import com.techmatrix18.repository.StorehouseRepository;
import com.techmatrix18.service.implementation.StorehouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

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

    @Autowired
    private StorehouseRepository storehouseRepository;
    @Autowired
    private StorehouseServiceImpl storehouseService;

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    public List<Storehouse> getStorehouses() throws ValidationException {
        return storehouseService.getAllStorehouses();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addStorehouse (@RequestParam Long cityId, @RequestParam String title, @RequestParam String description) {
        Storehouse s = new Storehouse();
        s.setCityId(cityId);
        s.setTitle(title);
        s.setDescription(description);
        storehouseService.addStorehouse(s);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateStorehouse (@RequestParam Long storehouseId, @RequestParam Long cityId, @RequestParam String title, @RequestParam String description) {
        Storehouse s = storehouseService.getStorehouseById(storehouseId);
        if (s.getId() != null) {
            s.setCityId(cityId);
            s.setTitle(title);
            s.setDescription(description);
            storehouseService.updateStorehouse(s);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{storehouseId:\\\\d+}")
    public @ResponseBody String deleteStorehouse (@PathVariable Long storehouseId) {
        Storehouse s = storehouseService.getStorehouseById(storehouseId);
        if (s.getId() != null) {
            storehouseService.deleteStorehouse(storehouseId);
        }
        return "Deleted";
    }
}

