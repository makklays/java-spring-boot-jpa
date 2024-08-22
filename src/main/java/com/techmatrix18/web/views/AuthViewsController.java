package com.techmatrix18.web.views;

import com.techmatrix18.model.Role;
import com.techmatrix18.model.User;
import com.techmatrix18.service.PositionService;
import com.techmatrix18.service.RoleService;
import com.techmatrix18.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthViewsController {
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final UserService userService;
    private final PositionService positionService;
    private final RoleService roleService;

    public AuthViewsController(UserService userService, PositionService positionService, RoleService roleService) {
        this.userService = userService;
        this.positionService = positionService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/user-details")
    public String login(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("positions", positionService.getAllPositions());

        return "auth/login";
    }

    @GetMapping("/my-registr")
    public String registr(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("positions", positionService.getAllPositions());

        return "auth/registr";
    }

    @PostMapping("/my-registr-post")
    public String registrPost(User user, Model model, BindingResult bindingResult) {
        /*if (bindingResult.hasErrors()) {
            logger.info("Have errors.....");
            model.addAttribute("user", user);
            model.addAttribute("positions", positionService.getAllPositions());
            return "auth/registr";
        }*/

        logger.info("aaaaaaaaaaaaaaa.....");

        // role - permissions ?
        Role role = roleService.getRoleByName("ROLE_USER");
        logger.info("aaaaaaaaaaaaaaa Role: " + role);

        // add role
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        // add password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode(user.getPassword());

        user.setPassword(result);
        //user.setRoles(roles);
        //userService.addUser(user);

        logger.info("aaaaaaaaaaaaaaa User: " + user);

        return "redirect:/login";
    }
}
