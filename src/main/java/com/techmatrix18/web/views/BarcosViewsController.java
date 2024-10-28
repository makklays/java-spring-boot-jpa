package com.techmatrix18.web.views;

import com.techmatrix18.model.*;
import com.techmatrix18.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.Set;

@Controller
@RequestMapping("/barcos")
public class BarcosViewsController {
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final BarcoService barcoService;
    private final StorehouseService storehouseService;
    private final CityService cityService;
    private final UserService userService;
    private final BarcoUserService barcoUserService;

    public BarcosViewsController(BarcoService barcoService, StorehouseService storehouseService, CityService cityService, UserService userService, BarcoUserService barcoUserService) {
        this.barcoService = barcoService;
        this.storehouseService = storehouseService;
        this.cityService = cityService;
        this.userService = userService;
        this.barcoUserService = barcoUserService;
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

        List<BarcoUser> barcoUsers = barcoUserService.getAllBarcoUserByBarcoId( Long.parseLong(barcoId) );

        //
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

    //----------- BarcoUser --------------------
    @GetMapping("/assign-add-user/{barcoId}")
    public String assignUser(Model model, @PathVariable String barcoId, BarcoUser barcoUser) {
        // users
        Barco barco = barcoService.getBarcoById(Long.parseLong(barcoId));
        model.addAttribute("barco", barco);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "barcos/assign-add-user";
    }

    @PostMapping("/assign-add-user-post/{barcoId}")
    public String assignUserPost(Model model, HttpServletRequest request, @PathVariable String barcoId, @Valid BarcoUser barcoUser, BindingResult bindingResult) throws ServletException, IOException {
        if (bindingResult.hasErrors()) {
            return "barcos/assign-add-user";
        }

        // get auth_user_id from session
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User authUser = userService.getUserByEmail(currentPrincipalName);

        // get Barco
        Barco barco = barcoService.getBarcoById(Long.parseLong(barcoId));

        // add Barco and authUser
        barcoUser.setBarco(barco);
        barcoUser.setAssignUserId(authUser);

        barcoUserService.addBarcoUser(barcoUser);

        return "redirect:/barcos/assign-add-user/" + Long.parseLong(barcoId);
    }

    @GetMapping("/assign-view-user/{barcoUserId}")
    public String assignViewUser(Model model, @PathVariable String barcoUserId, BarcoUser barcoUser) {
        BarcoUser barcoUserOne = barcoUserService.getBarcoUserById(Long.parseLong(barcoUserId));
        model.addAttribute("barco_user", barcoUserOne);

        return "barcos/assign-view-user";
    }

    @GetMapping("/assign-delete-user/{barcoUserId}")
    public void deleteBarcoUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String barcoUserId) throws IOException {
        BarcoUser barcoUser = barcoUserService.getBarcoUserById(Long.parseLong(barcoUserId));
        if (barcoUser.getId() != null) {
            barcoUserService.deleteBarcoUser(Long.parseLong(barcoUserId));
        }

        response.sendRedirect("/barcos/"+barcoUser.getBarco().getId());
    }
}

