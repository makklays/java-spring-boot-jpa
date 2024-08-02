package com.techmatrix18.web.api;

import com.techmatrix18.model.Barco;
import com.techmatrix18.repository.BarcoRepository;
import com.techmatrix18.service.BarcoService;
import com.techmatrix18.service.CategoryService;
import com.techmatrix18.service.implementation.BarcoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Simple Controller for Barco
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/barcos")
public class BarcoController {

    private final BarcoService barcoService;

    public BarcoController(BarcoService barcoService) {
        this.barcoService = barcoService;
    }

    private final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    private final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    private final List<Barco> BARCOS = Stream.of(
            new Barco("Barco 1", "Description 1", 50, 23000, 120, timestamp, timestamp),
            new Barco("Barco 2", "Description 2", 60, 26000, 150, timestamp, timestamp),
            new Barco("Barco 3", "Description 3", 80, 28000, 180, timestamp, timestamp)
    ).collect(Collectors.toList());

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Barco> getBarcos() throws ValidationException {
        return barcoService.getAllBarcos();
        //return BARCOS;
    }

    @GetMapping(path = "/{id}")
    public Barco getById(@PathVariable Long id) {
        return BARCOS.stream().filter(barco -> barco.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Barco create(@RequestBody Barco barco) {
        this.BARCOS.add(barco);
        return barco;
    }

    @DeleteMapping(path="/{id}")
    public void barcoById(@PathVariable Long id) {
        this.BARCOS.removeIf(barco -> barco.getId().equals(id));
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

