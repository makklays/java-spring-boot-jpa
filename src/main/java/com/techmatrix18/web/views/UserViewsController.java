package com.techmatrix18.web.views;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import com.techmatrix18.service.PositionService;
import com.techmatrix18.service.UserService;
import com.techmatrix18.service.implementation.PositionServiceImpl;
import com.techmatrix18.service.implementation.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Simple controller for User
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Controller
@RequestMapping("/")
public class UserViewsController {
    // log
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final UserService userService;
    private final PositionService positionService;

    public UserViewsController(UserService userService, PositionService positionService) {
        this.userService = userService;
        this.positionService = positionService;
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("vv", "V-V-V");
        return "menu";
    }

    @GetMapping(path = "/simple")
    public String simple1(Model model) {
        //model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("vv", "V-V-V");
        return "simple";
    }

    @GetMapping(path = "/users/list")
    public ModelAndView viewUsers() {
        ModelAndView mav = new ModelAndView("list");
        //mav.addObject("greeting", "GeeksForGeeks Welcomes you to Spring!");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    @GetMapping(path = "/users/list1")
    public String list1(Model model) {
        //model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("vv", "V-V-V");
        return "qa-proba";
    }

    // ---- thymeleaf example -------
    @GetMapping("/users/web-user-list-paging")
    public ModelAndView listUserViewPaging() {
        return findPaginated(1);
    }
    @GetMapping("/users/web-user-list")
    public ModelAndView listUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("list");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }
    @GetMapping("/users/web-user-add")
    public ModelAndView createUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        mav.addObject("user", new User());
        mav.addObject("positions", positionService.getAllPositions());
        return mav;
    }
    @PostMapping("/users/web-user-add")
    public ModelAndView createUser(@Valid User user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            mav.setViewName("add");
            mav.addObject("user", user);
            mav.addObject("positions", positionService.getAllPositions());
            return mav;
        }
        userService.addUser(user);
        mav.addObject("users", userService.getAllUsers());
        mav.setViewName("list");
        logger.info("Form submitted successfully.");
        return mav;
    }
    @GetMapping("/users/web-user-list-paging/page/{pageNo}")
    public ModelAndView findPaginated(@PathVariable(value = "pageNo") int pageNo) {
        int pageSize = 3;

        Page <User> page = userService.findPaginated(pageNo, pageSize);
        List <User> listUsers = page.getContent();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("list");
        mav.addObject("currentPage", pageNo);
        mav.addObject("totalPages", page.getTotalPages());
        mav.addObject("totalItems", page.getTotalElements());
        mav.addObject("users", listUsers);
        return mav;
    }
    // ----------- end --------------

    @GetMapping("/users/example")
    public String toExample(Model model) {
        model.addAttribute("example", "Garry");
        return "/users/example";
    }
    @PostMapping("/users/example/add")
    public String createUser(@RequestParam String firstname, @RequestParam String lastname) {
        System.out.printf("First name: %s; Last name: %s \n", firstname, lastname);
        return "redirect:/users";
    }
}
