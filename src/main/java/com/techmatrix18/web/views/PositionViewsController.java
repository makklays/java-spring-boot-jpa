package com.techmatrix18.web.views;

import com.techmatrix18.model.Category;
import com.techmatrix18.model.Position;
import com.techmatrix18.service.CategoryService;
import com.techmatrix18.service.PositionService;
import com.techmatrix18.service.UserService;
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

@Controller
@RequestMapping("/positions")
public class PositionViewsController {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final PositionService positionService;
    private final UserService userService;

    public PositionViewsController(PositionService positionService, UserService userService) {
        this.positionService = positionService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        //
        model.addAttribute("positions", positionService.getAllPositions());
        return "positions/list";
    }

    @GetMapping("/add")
    public String add(Model model, Position position) {
        // cities
        //List<City> cities = cityService.getAllCities();
        model.addAttribute("position", position);

        return "positions/add";
    }

    @PostMapping("/add-post")
    public String addPost(Model model, @Valid Position position, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "positions/add";
        }

        positionService.addPosition(position);

        return "redirect:/positions/list";
    }

    @GetMapping("/edit/{positionId}")
    public String edit(@PathVariable Long positionId, Model model){
        Optional<Position> position = positionService.getPositionById(positionId);
        if (position.get().getId() != null) {
            model.addAttribute("position", position.get());
            logger.info("Position found..");
        } else {
            model.addAttribute("position", null);
            logger.warn("Error! Position not found..");
        }


        return "positions/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, @Valid Position position, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            position.setId(id);
            return "positions/edit";
        }

        positionService.updatePosition(position);

        return "redirect:/positions/list";
    }

    @GetMapping("/delete/{positionId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long positionId) throws IOException {
        Optional<Position> position = positionService.getPositionById(positionId);
        if (position.get().getId() != null) {
            positionService.deletePosition(positionId);
        }

        response.sendRedirect("/positions/list");
    }

    @GetMapping("/{positionId}")
    public String view(Model model, @PathVariable String positionId) {
        Optional<Position> position = positionService.getPositionById(Long.parseLong(positionId));
        if (position.get().getId() != null) {
            model.addAttribute("position", position.get());
            logger.info("Position found..");
        } else {
            model.addAttribute("position", null);
            logger.info("Error! Position not found..");
        }

        model.addAttribute("users", userService.getUsersByPositionId(Long.parseLong(positionId)));

        return "positions/view";
    }
}

