package com.techmatrix18.web.views;

import com.techmatrix18.model.City;
import com.techmatrix18.model.Storehouse;
import com.techmatrix18.service.CityService;
import com.techmatrix18.service.StorehouseService;
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
import java.util.Optional;

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
    public String list(Model model) {
        //
        model.addAttribute("storehouses", storehouseService.getAllStorehouses());
        return "storehouses/list";
    }

    @GetMapping("/add")
    public String add(Model model, Storehouse storehouse) {
        // cities
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        return "storehouses/add";
    }

    @PostMapping("/add-post")
    public String addPost(Model model, @Valid Storehouse storehouse, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "storehouses/add";
        }

        storehouseService.addStorehouse(storehouse);

        return "redirect:/storehouses/list";
    }

    @GetMapping("/edit/{storehouseId}")
    public String edit(HttpServletRequest request, HttpServletResponse response, @PathVariable Long storehouseId, Model model) throws Exception {
        Optional<Storehouse> storehouse = storehouseService.getStorehouseById(storehouseId);
        if (storehouse.isPresent()) {
            model.addAttribute("storehouse", storehouse.get());
            //model.addAttribute("city", cityService.getCityById(storehouse.getCityId()));
            logger.info("Storehouse found..");
        } else {
            model.addAttribute("storehouse", null);
            logger.warn("Error! Storehouse not found..");
        }

        // cities
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        return "storehouses/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, HttpServletRequest request, @Valid Storehouse storehouse, BindingResult bindingResult, Model model) throws ServletException, IOException {
        if (bindingResult.hasErrors()) {
            storehouse.setId(id);
            return "storehouses/edit";
        }

        //-----------
        // upload file
        Part part = request.getPart("photo1");

        //get the InputStream to store the file somewhere
        InputStream fileInputStream = part.getInputStream();
        //
        String fileName = part.getSubmittedFileName();
        //
        File fileToSave = new File("/home/alexander/IdeaProjects/spring-jpa-boot-data-security/src/main/resources/static/uploads/" + fileName);

        logger.info("FileInputStream: {}", fileInputStream);
        logger.info("FileToSave: {}", fileToSave.toPath());
        logger.info("Photo: {}", fileName);

        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
        storehouse.setPhoto(fileName);
        //Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
        //get the URL of the uploaded file
        //String fileUrl = "http://localhost:8080/uploaded-files/" + part.getSubmittedFileName();
        //create output HTML that uses the
        //response.getOutputStream().println("<p><a href=\"" + fileUrl + "\">" + fileUrl + "</a></p>");

        String cityId = request.getParameter("city_id");
        Optional<City> city = cityService.getCityById(Long.parseLong(cityId));
        storehouse.setCity(city.get());

        storehouseService.updateStorehouse(storehouse);

        return "redirect:/storehouses/list";
    }

    @GetMapping("/delete/{storehouseId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long storehouseId) throws IOException {
        Optional<Storehouse> storehouse = storehouseService.getStorehouseById(storehouseId);
        if (storehouse.isPresent()) {
            storehouseService.deleteStorehouse(storehouseId);
        }

        response.sendRedirect("/storehouses/list");
    }

    @GetMapping("/{storehouseId}")
    public String view(Model model, @PathVariable String storehouseId) {
        Optional<Storehouse> storehouse = storehouseService.getStorehouseById(Long.parseLong(storehouseId));
        if (storehouse.isPresent()) {
            model.addAttribute("storehouse", storehouse.get());
            //model.addAttribute("city", cityService.getCityById(storehouse.getCityId()));
            logger.info("Storehouse found..");
        } else {
            model.addAttribute("storehouse", null);
            logger.info("Error! Storehouse not found..");
        }

        return "storehouses/view";
    }
}

