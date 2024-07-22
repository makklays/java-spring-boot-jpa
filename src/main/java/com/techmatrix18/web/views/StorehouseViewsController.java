package com.techmatrix18.web.views;

import com.techmatrix18.model.City;
import com.techmatrix18.model.Storehouse;
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
@RequestMapping("/storehouses")
public class StorehouseViewsController {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final StorehouseService storehouseService;
    private final CityService cityService;

    public StorehouseViewsController(StorehouseService storehouseService, CityService cityService) {
        this.storehouseService = storehouseService;
        this.cityService = cityService;
    }

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        //
        model.addAttribute("storehouses", storehouseService.getAllStorehouses());
        return "storehouses/list";
    }

    @GetMapping("/add")
    public String add(Storehouse storehouse) {
        return "storehouses/add";
    }

    @PostMapping("/add-post")
    public String addPost(HttpServletRequest request, HttpServletResponse response, Model model, @Valid Storehouse storehouse, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "storehouses/add";
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String city_id = request.getParameter("city_id");

        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        Storehouse st = new Storehouse();
        st.setTitle(title);
        st.setDescription(description);
        st.setCityId(Long.parseLong(city_id));
        storehouseService.addStorehouse(st);

        System.out.printf("Title: %s; description: %s \n", title, description);

        return "redirect:/storehouses/list";
    }

    @GetMapping("/edit/{storehouseId}")
    public String edit(HttpServletRequest request, HttpServletResponse response, @PathVariable Long storehouseId, Model model) throws Exception {
        Storehouse storehouse = storehouseService.getStorehouseById(storehouseId);
        if (storehouse.getId() != null) {
            model.addAttribute("storehouse", storehouse);
            logger.info("Storehouse found..");
        } else {
            model.addAttribute("storehouse", null);
            logger.warn("Error! Storehouse not found..");
        }

        return "storehouse/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, @Valid Storehouse storehouse, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            storehouse.setId(id);
            return "storehouses/edit";
        }

        storehouseService.updateStorehouse(storehouse);

        return "redirect:/storehouses/list";
    }

    @GetMapping("/delete/{storehouseId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long storehouseId) throws IOException {
        Storehouse storehouse = storehouseService.getStorehouseById(storehouseId);
        if (storehouse.getId() != null) {
            storehouseService.deleteStorehouse(storehouseId);
        }

        response.sendRedirect("/storehouses/list");
    }

    @GetMapping("/{storehouseId}")
    public String view(Model model, @PathVariable String storehouseId) {
        Storehouse storehouse = storehouseService.getStorehouseById(Long.parseLong(storehouseId));
        if (storehouse.getId() != null) {
            model.addAttribute("storehouse", storehouse);
            logger.info("Storehouse found..");
        } else {
            model.addAttribute("storehouse", null);
            logger.info("Error! Storehouse not found..");
        }

        return "storehouses/view";
    }
}
