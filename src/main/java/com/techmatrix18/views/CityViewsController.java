package com.techmatrix18.views;

import com.techmatrix18.model.City;
import com.techmatrix18.service.CityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityViewsController {

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

    @GetMapping("/add")
    public String add() {
        return "cities/add";
    }

    @PostMapping("/add-post")
    public void addPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        City city = new City();
        city.setTitle(title);
        city.setDescription(description);
        cityService.addCity(city);

        System.out.printf("First name: %s; Last name: %s \n", title, description);

        //return "redirect/:cities";
        response.sendRedirect("/list");
    }

    @GetMapping(path = "/edit/{cityId}")
    public String edit(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable Long cityId) throws IOException {
        City city = cityService.getCityById(cityId);
        //ModelAndView mav = new ModelAndView("cities-edit");
        if (city.getId() != null) {
            //mav.addObject("city", cityService.getCityById(cityId));
            model.addAttribute("city", city);
            System.out.println("City found..");
        } else {
            model.addAttribute("city", null);
            //mav.addObject("city", null);
            System.out.println("Error! City not found..");
        }

        return "cities/edit";
    }

    @PostMapping(path = "/edit/{cityId}")
    public void editPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String cityId = request.getParameter("cityId");

        City city = cityService.getCityById(Long.parseLong(cityId));
        ModelAndView mav = new ModelAndView("cities-edit");
        if (city.getId() != null) {
            city.setTitle(title);
            city.setDescription(description);
            cityService.updateCity(city);
            System.out.println("City updated successfully!");
        } else {
            mav.addObject("city", null);
            System.out.println("Error! City not found..");
        }

        response.sendRedirect("/edit/" + city.getId() );
    }

    @GetMapping(path = "/delete/{cityId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long cityId) throws IOException {
        City city = cityService.getCityById(cityId);
        if (city.getId() != null) {
            cityService.deleteCity(cityId);
        }

        response.sendRedirect("/list");
    }

    @GetMapping(path = "/{cityId}")
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

