package com.techmatrix18.web.api;

import com.techmatrix18.model.Barco;
import com.techmatrix18.repository.BarcoRepository;
import com.techmatrix18.service.implementation.BarcoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/************************************
 * Author: Alexander Kuziv
 *  Email: makklays@gmail.com
 *   Date: 19-02-2024
 ***********************************/

@RestController
@RequestMapping("/api/v1/barcos")
public class BarcoController {

    @Autowired
    private BarcoRepository barcoRepository;
    @Autowired
    private BarcoImpl barcoService;

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/")
    public List<Barco> getBarcos() throws ValidationException {
        return barcoService.getAllBarcos();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addBarco (@RequestParam String title, @RequestParam String description, @RequestParam Integer year, @RequestParam Integer weight, @RequestParam Integer speedometer) {
        Barco b = new Barco();
        b.setTitle(title);
        b.setDescription(description);
        b.setYear(year);
        b.setWeight(weight);
        b.setSpeedometer(speedometer);
        barcoService.addBarco(b);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateBarco (@RequestParam Long barcoId, @RequestParam String title, @RequestParam String description, @RequestParam Integer year, @RequestParam Integer weight, @RequestParam Integer speedometer) {
        Barco b = barcoService.getBarcoById(barcoId);
        if (b.getId() != null) {
            b.setTitle(title);
            b.setDescription(description);
            b.setYear(year);
            b.setWeight(weight);
            b.setSpeedometer(speedometer);
            barcoService.updateBarco(b);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{barcoId:\\\\d+}")
    public @ResponseBody String deleteBarco (@PathVariable Long barcoId) {
        Barco b = barcoService.getBarcoById(barcoId);
        if (b.getId() != null) {
            barcoService.deleteBarco(barcoId);
        }
        return "Deleted";
    }
}

