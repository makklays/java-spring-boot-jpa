package com.techmatrix18.views;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
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
@RequestMapping("/users")
public class WebUserController {
    // log
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PositionServiceImpl positionService;

    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("users-index");
        //mav.addObject("greeting", "GeeksForGeeks Welcomes you to Spring!");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    @GetMapping(path = "/list1")
    public String list1(Model model) {
        //model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("vv", "V-V-V");
        return "qa-proba";
    }

    @GetMapping(path = "/list2")
    public ModelAndView viewUsers2() {
        ModelAndView mav = new ModelAndView("users-index");
        //mav.addObject("greeting", "GeeksForGeeks Welcomes you to Spring!");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    // ---- thymeleaf example -------
    @GetMapping("/web-user-list-paging")
    public ModelAndView listUserViewPaging() {
        return findPaginated(1);
    }
    @GetMapping("/web-user-list")
    public ModelAndView listUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("list");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }
    @GetMapping("/web-user-add")
    public ModelAndView createUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        mav.addObject("user", new User());
        mav.addObject("positions", positionService.getAllPositions());
        return mav;
    }
    @PostMapping("/web-user-add")
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
    @GetMapping("/web-user-list-paging/page/{pageNo}")
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

    @GetMapping("/example")
    public String toExample(Model model) {
        model.addAttribute("example", "Garry");
        return "/users/example";
    }
    @PostMapping("/example/add")
    public String createUser(@RequestParam String firstname, @RequestParam String lastname) {
        System.out.printf("First name: %s; Last name: %s \n", firstname, lastname);
        return "redirect:/users";
    }
}

