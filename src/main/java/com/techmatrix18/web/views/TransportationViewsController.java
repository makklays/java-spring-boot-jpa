package com.techmatrix18.web.views;

import com.techmatrix18.model.Transportation;
import com.techmatrix18.model.User;
import com.techmatrix18.service.*;
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
import java.util.Optional;

/**
 * Simple controller for Transportation
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Controller
@RequestMapping("/transportations")
public class TransportationViewsController {
    // log
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final TransportationService transportationService;
    private final BarcoService barcoService;
    private final StorehouseService storehouseService;

    public TransportationViewsController(TransportationService transportationService, BarcoService barcoService, StorehouseService storehouseService) {
        this.transportationService = transportationService;
        this.barcoService = barcoService;
        this.storehouseService = storehouseService;
    }

    @GetMapping(path = "/list")
    public String listTransportation(Model model) {
        model.addAttribute("transportations", transportationService.getAllTransportations());

        return "transportations/list";
    }

    @GetMapping("/add")
    public String add(Model model, Transportation transportation) {
        model.addAttribute("transportation", transportation);
        model.addAttribute("barcos", barcoService.getAllBarcos());
        model.addAttribute("storehouses", storehouseService.getAllStorehouses());

        return "transportations/add";
    }

    @PostMapping("/add-post")
    public String addPost(Model model, @Valid Transportation transportation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "transportations/add";
        }

        transportationService.addTransportation(transportation);

        return "redirect:/transportations/list";
    }

    @GetMapping("/edit/{transportationId}")
    public String edit(HttpServletRequest request, HttpServletResponse response, @PathVariable Long transportationId, Model model) throws Exception {
        Optional<Transportation> transportation = transportationService.getTransportationById(transportationId);
        if (transportation.isPresent()) {
            model.addAttribute("transportation", transportation.get());
            logger.info("Transportation found..");
        } else {
            model.addAttribute("Transportation", null);
            logger.warn("Error! User not found..");
        }

        //
        model.addAttribute("barcos", barcoService.getAllBarcos());
        model.addAttribute("storehouses", storehouseService.getAllStorehouses());

        return "transportations/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, @Valid Transportation transportation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            transportation.setId(id);
            return "users/edit";
        }

        transportationService.updateTransportation(transportation);

        return "redirect:/transportations/list";
    }

    @GetMapping("/delete/{transportationId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long transportationId) throws IOException {
        Optional<Transportation> transportation = transportationService.getTransportationById(transportationId);
        if (transportation.isPresent()) {
            transportationService.deleteTransportation(transportationId);
        }

        response.sendRedirect("/transportations/list");
    }

    @GetMapping("/{transportationId}")
    public String view(Model model, @PathVariable String transportationId) {
        Optional<Transportation> transportation = transportationService.getTransportationById(Long.parseLong(transportationId));
        if (transportation.isPresent()) {
            model.addAttribute("transportation", transportation.get());
            logger.info("Transportation found..");
        } else {
            model.addAttribute("transportation", null);
            logger.info("Error! Transportation not found..");
        }

        return "transportations/view";
    }
}

