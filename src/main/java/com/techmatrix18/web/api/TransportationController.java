package com.techmatrix18.web.api;

import com.techmatrix18.model.Transportation;
import com.techmatrix18.service.TransportationService;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Simple controller for Transportation
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/transportations")
public class TransportationController {

    private final TransportationService transportationService;

    public TransportationController(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    public List<Transportation> getTransportations() throws ValidationException {
        return transportationService.getAllTransportations();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addTransportation (@RequestParam Long barcoId, @RequestParam Long storehouseId, @RequestParam Integer distance, @RequestParam Integer weight) {
        Transportation t = new Transportation();
        //t.setBarco(barco);
        //t.setStorehouse(storehouse);
        t.setDistance(distance);
        t.setWeight(weight);
        transportationService.addTransportation(t);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateTransportation (@RequestParam Long transportationId, @RequestParam Long barcoId, @RequestParam Long storehouseId, @RequestParam Integer distance, @RequestParam Integer weight) {
        Transportation t = transportationService.getTransportationById(transportationId);
        if (t.getId() != null) {
            //t.setBarco(barco);
            //t.setStorehouse(storehouse);
            t.setDistance(distance);
            t.setWeight(weight);
            transportationService.updateTransportation(t);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{transportationId:\\\\d+}")
    public @ResponseBody String deleteTransportation (@PathVariable Long transportationId) {
        Transportation t = transportationService.getTransportationById(transportationId);
        if (t.getId() != null) {
            transportationService.deleteTransportation(transportationId);
        }
        return "Deleted";
    }
}

