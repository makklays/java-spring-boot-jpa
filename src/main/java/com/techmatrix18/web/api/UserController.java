package com.techmatrix18.web.api;

import com.techmatrix18.model.Role;
import com.techmatrix18.model.User;
import com.techmatrix18.service.PositionService;
import com.techmatrix18.service.RoleService;
import com.techmatrix18.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.*;

/**
 * Simple REST controller for User
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // log
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final PositionService positionService;
    private final RoleService roleService;

    public UserController(UserService userService, PositionService positionService, RoleService roleService) {
        this.userService = userService;
        this.positionService = positionService;
        this.roleService = roleService;
    }

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @RequestMapping(value = "/products.do", method = RequestMethod.GET)
    public String handleRequest() {
        return "products";
    }

    @GetMapping(path = "/all")
    public List<User> getUsers(HttpServletRequest request) throws ValidationException {
        String roleParam = request.getParameter("role");
        Role role = roleService.getRoleByName(roleParam);
        if (role!=null) {
            return userService.getUsersByRole(role);
        } else {
            return userService.getAllUsers();
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addUser (@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam String password, @RequestParam String positionId) {
        User u = new User();
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setEmail(email);
        u.setPassword(password);
        //Long longPositionId = Long.parseLong(positionId);
        //u.setPositionId(longPositionId);

        System.out.println(u.toString());

        userService.addUser(u);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateUser (@RequestParam String userId, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam String password, @RequestParam String positionId) {
        Long longUserId = Long.parseLong(userId);
        User u = userService.getUserById(longUserId);
        if (!u.getEmail().isEmpty()) {
            u.setFirstname(firstname);
            u.setLastname(lastname);
            u.setEmail(email);
            u.setPassword(password);
            //Long longPositionId = Long.parseLong(positionId);
            //u.setPositionId(longPositionId);
            userService.updateUser(u);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{userId}")
    public @ResponseBody String deleteUser (@PathVariable String userId) {
        Long longUserId = Long.parseLong(userId);
        User u = userService.getUserById(longUserId);

        System.out.println("userId =====> " + u.getId());

        if (u.getId() != null) {
            userService.deleteUser(longUserId);
        } else {
            return "Hasn't userId = " + userId;
        }
        return "Deleted";
    }
}

