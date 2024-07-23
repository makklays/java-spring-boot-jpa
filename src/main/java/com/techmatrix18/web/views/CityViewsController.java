package com.techmatrix18.web.views;

import com.techmatrix18.model.City;
import com.techmatrix18.service.CityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityViewsController implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final CityService cityService;

    public CityViewsController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<City> list = cityService.getAllCities();
        model.addAttribute("cities", list);
        return "cities/list";
    }

    @PostMapping("/add-post")
    public String addPost(HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "cities/add";
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        City city = new City();
        city.setTitle(title);
        city.setDescription(description);
        cityService.addCity(city);

        System.out.printf("First name: %s; Last name: %s \n", title, description);

        return "redirect:/cities/list";
    }

    @GetMapping(path = "/edit/{cityId}")
    public String edit(Model model, @PathVariable Long cityId) throws IOException {
        City city = cityService.getCityById(cityId);
        if (city.getId() != null) {
            model.addAttribute("city", city);
            logger.info("City found..");
        } else {
            model.addAttribute("city", null);
            logger.warn("Error! City not found..");
        }

        return "cities/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, @Valid City city, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            city.setId(id);
            return "cities/edit";
        }

        cityService.updateCity(city);

        return "redirect:/cities/list";
    }

    @GetMapping("/delete/{cityId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long cityId) throws IOException {
        City city = cityService.getCityById(cityId);
        if (city.getId() != null) {
            cityService.deleteCity(cityId);
        }

        response.sendRedirect("/cities/list");
    }

    @GetMapping("/{cityId}")
    public String view(Model model, @PathVariable String cityId) {
        City city = cityService.getCityById(Long.parseLong(cityId));
        if (city.getId() != null) {
            model.addAttribute("city", city);
           logger.info("City found..");
        } else {
            model.addAttribute("city", null);
            logger.info("Error! City not found..");
        }

        return "cities/view";
    }
}

