package com.techmatrix18.web.views;

import com.techmatrix18.model.User;
import com.techmatrix18.service.PositionService;
import com.techmatrix18.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthViewsController {
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final UserService userService;
    private final PositionService positionService;

    public AuthViewsController(UserService userService, PositionService positionService) {
        this.userService = userService;
        this.positionService = positionService;
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

//    @PostMapping("/login")
//    public String loginPost(HttpServletRequest request, Model model, @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "auth/login";
//        }
//
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        userService.getUserByEmailAndPassword(email, password);
//        //userService.getUsersByEmail(email);
//
//        return "redirect:/menu";
//    }

    @GetMapping("/my-registr")
    public String registr(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("positions", positionService.getAllPositions());

        return "auth/registr";
    }

    @PostMapping("/my-registr-post")
    public String registrPost(Model model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/registr";
        }

        userService.addUser(user);

        return "redirect:/my-login";
    }
}
