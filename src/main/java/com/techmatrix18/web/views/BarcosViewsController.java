package com.techmatrix18.web.views;

import com.techmatrix18.model.Barco;
import com.techmatrix18.model.City;
import com.techmatrix18.model.Storehouse;
import com.techmatrix18.model.User;
import com.techmatrix18.service.BarcoService;
import com.techmatrix18.service.CityService;
import com.techmatrix18.service.StorehouseService;
import com.techmatrix18.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/barcos")
public class BarcosViewsController {
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final BarcoService barcoService;
    private final StorehouseService storehouseService;
    private final CityService cityService;
    private final UserService userService;

    public BarcosViewsController(BarcoService barcoService, StorehouseService storehouseService, CityService cityService, UserService userService) {
        this.barcoService = barcoService;
        this.storehouseService = storehouseService;
        this.cityService = cityService;
        this.userService = userService;
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
    public String addPost(Model model, HttpServletRequest request, @Valid Barco barco, BindingResult bindingResult) throws ServletException, IOException {
        if (bindingResult.hasErrors()) {
            return "barcos/add";
        }

        //-----------
        // upload file
        Part part = request.getPart("photo1");
        if (part != null) {
            //get the InputStream to store the file somewhere
            InputStream fileInputStream = part.getInputStream();
            String fileName = part.getSubmittedFileName();
            File fileToSave = new File("/home/alexander/IdeaProjects/spring-jpa-boot-data-security/src/main/resources/static/uploads/barcos/" + fileName);
            Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
            barco.setPhoto(fileName);
        }
        //------------

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
    public String editPost(@PathVariable("id") long id, HttpServletRequest request, @Valid Barco barco, BindingResult bindingResult, Model model) throws ServletException, IOException {
        if (bindingResult.hasErrors()) {
            barco.setId(id);
            return "barcos/edit";
        }

        //--------------
        // upload file
        Part part = request.getPart("photo1");
        if (part != null) {
            InputStream fileInputStream = part.getInputStream();
            String fileName = part.getSubmittedFileName();
            File fileToSave = new File("/home/alexander/IdeaProjects/spring-jpa-boot-data-security/src/main/resources/static/uploads/barcos/" + fileName);
            Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
            barco.setPhoto(fileName);
        }
        //--------------

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

        List<User> barcoUsers = null; // userService.getAllUsers();

        if (barco.getId() != null) {
            model.addAttribute("barco", barco);
            model.addAttribute("barco_users", barcoUsers);
            logger.info("Barco found..");
        } else {
            model.addAttribute("barco", null);
            logger.info("Error! Storehouse not found..");
        }

        return "barcos/view";
    }
}

