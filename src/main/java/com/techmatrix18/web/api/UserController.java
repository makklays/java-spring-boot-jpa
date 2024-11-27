package com.techmatrix18.web.api;

import com.techmatrix18.model.Role;
import com.techmatrix18.model.User;
import com.techmatrix18.service.PositionService;
import com.techmatrix18.service.RoleService;
import com.techmatrix18.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
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

    @GetMapping(path = "/all", produces = "application/json;charset=UTF-8")
    public List<User> getUsers(HttpServletRequest request) {
        String roleParam = request.getParameter("role");
        Role role = roleService.getRoleByName(roleParam);
        if (role!=null) {
            //return userService.getUserByUserRoleId(userRoleId);
            return null;
        } else {
            return userService.getAllUsers();
        }
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public Object getOneUser(@PathVariable String id) {
        Long userId = Long.parseLong(id);
        User user = userService.getUserById(userId);
        if (user != null) {
            return user;
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find user with ID=" + id + "\"}";
        }
    }

    @GetMapping(path = "/page", produces = "application/json;charset=UTF-8")
    public Object getPage(@RequestParam String pageNo, @RequestParam String pageSize) {
        int pNo = Integer.parseInt(pageNo);
        int pSize = Integer.parseInt(pageSize);

        Page<User> users = userService.findPaginated(pNo, pSize);
        if (users != null) {
            return users;
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find users with pageNo=" + pageNo + " and pageSize=" + pageSize + " \"}";
        }
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public @ResponseBody String addUser (@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam String password, @RequestParam String positionId) {
        User u = new User();
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setEmail(email);
        u.setPassword(password);
        //Long longPositionId = Long.parseLong(positionId);
        //u.setPositionId(longPositionId);

        System.out.println(u.toString());

        if (userService.addUser(u)) {
            return "{\"status\": \"success\", \"message\": \"User added successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't add user\"}";
        }
    }

    @PatchMapping(path = "/update", produces = "application/json;charset=UTF-8")
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
            if (userService.updateUser(u)) {
                return "{\"status\": \"success\", \"message\": \"User updated successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't update user\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find user with ID=" + userId + "\"}";
        }
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json;charset=UTF-8")
    public @ResponseBody String deleteUser (@PathVariable String id) {
        Long userId = Long.parseLong(id);
        User u = userService.getUserById(userId);

        System.out.println("userId =====> " + u.getId());

        if (u.getId() != null) {
            if (userService.deleteUser(userId)) {
                return "{\"status\": \"success\", \"message\": \"User deleted successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't delete user\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find user with ID=" + userId + "\"}";
        }
    }
}

