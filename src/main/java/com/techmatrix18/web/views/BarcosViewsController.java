package com.techmatrix18.web.views;

import com.techmatrix18.model.Barco;
import com.techmatrix18.model.City;
import com.techmatrix18.model.Storehouse;
import com.techmatrix18.service.BarcoService;
import com.techmatrix18.service.CityService;
import com.techmatrix18.service.StorehouseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/barcos")
public class BarcosViewsController {
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final BarcoService barcoService;
    private final StorehouseService storehouseService;
    private final CityService cityService;

    public BarcosViewsController(BarcoService barcoService, StorehouseService storehouseService, CityService cityService) {
        this.barcoService = barcoService;
        this.storehouseService = storehouseService;
        this.cityService = cityService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        //
        model.addAttribute("barcos", barcoService.getAllBarcos());
        return "barcos/list";
    }

    @GetMapping("/add")
    public String add(Model model, Barco barco) {
        // cities
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        return "barcos/add";
    }

    @PostMapping("/add-post")
    public String addPost(Model model, @Valid Barco barco, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "barcos/add";
        }

        barcoService.addBarco(barco);

        return "redirect:/barcos/list";
    }

    @GetMapping("/edit/{barcoId}")
    public String edit(HttpServletRequest request, HttpServletResponse response, @PathVariable Long barcoId, Model model) throws Exception {
        Barco barco = barcoService.getBarcoById(barcoId);
        if (barco.getId() != null) {
            model.addAttribute("barco", barco);
            logger.info("Storehouse found..");
        } else {
            model.addAttribute("barco", null);
            logger.warn("Error! Storehouse not found..");
        }

        // cities
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        return "barcos/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, @Valid Barco barco, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            barco.setId(id);
            return "barcos/edit";
        }

        barcoService.updateBarco(barco);

        return "redirect:/barcos/list";
    }

    @GetMapping("/delete/{barcoId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long barcoId) throws IOException {
        Barco barco = barcoService.getBarcoById(barcoId);
        if (barco.getId() != null) {
            barcoService.deleteBarco(barcoId);
        }

        response.sendRedirect("/barcos/list");
    }

    @GetMapping("/{barcoId}")
    public String view(Model model, @PathVariable String barcoId) {
        Barco barco = barcoService.getBarcoById(Long.parseLong(barcoId));
        if (barco.getId() != null) {
            model.addAttribute("barco", barco);
            logger.info("Barco found..");
        } else {
            model.addAttribute("barco", null);
            logger.info("Error! Storehouse not found..");
        }

        return "barcos/view";
    }
}

