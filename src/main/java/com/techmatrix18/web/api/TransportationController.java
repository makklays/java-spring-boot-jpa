package com.techmatrix18.web.api;

import com.techmatrix18.model.Transportation;
import com.techmatrix18.service.TransportationService;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/all", produces = "application/json;charset=UTF-8")
    public List<Transportation> getTransportations() throws ValidationException {
        return transportationService.getAllTransportations();
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public Object getOneTransportation(@PathVariable String id) throws ValidationException {
        Long tranId = Long.parseLong(id);
        Optional<Transportation> tran = transportationService.getTransportationById(tranId);
        if (tran.isPresent()) {
            return tran.get();
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find storehouse with ID=" + id + "\"}";
        }
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public @ResponseBody String addTransportation (@RequestParam Long barcoId, @RequestParam Long storehouseId, @RequestParam Integer distance, @RequestParam Integer weight) {
        Transportation t = new Transportation();
        //t.setBarco(barco);
        //t.setStorehouse(storehouse);
        t.setDistance(distance);
        t.setWeight(weight);
        if (transportationService.addTransportation(t)) {
            return "{\"status\": \"success\", \"message\": \"Transportation added successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't add transportation\"}";
        }
    }

    @PatchMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public @ResponseBody String updateTransportation (@RequestParam Long transportationId, @RequestParam Long barcoId, @RequestParam Long storehouseId, @RequestParam Integer distance, @RequestParam Integer weight) {
        Optional<Transportation> t = transportationService.getTransportationById(transportationId);
        if (t.isPresent()) {
            //t.setBarco(barco);
            //t.setStorehouse(storehouse);
            t.get().setDistance(distance);
            t.get().setWeight(weight);
            if (transportationService.updateTransportation(t.get())) {
                return "{\"status\": \"success\", \"message\": \"Transportation updated successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't update transportation\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find transportation with ID=" + transportationId + "\"}";
        }
    }

    @DeleteMapping(path = "/delete/{transportationId}", produces = "application/json;charset=UTF-8")
    public @ResponseBody String deleteTransportation (@PathVariable Long transportationId) {
        Optional<Transportation> t = transportationService.getTransportationById(transportationId);
        if (t.isPresent()) {
            if (transportationService.deleteTransportation(transportationId)) {
                return "{\"status\": \"success\", \"message\": \"Transportation deleted successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't delete transportation\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find transportation with ID=" + transportationId + "\"}";
        }
    }
}

