package com.techmatrix18.web.api;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import com.techmatrix18.service.implementation.PositionServiceImpl;
import com.techmatrix18.service.implementation.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PositionServiceImpl positionService;

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @RequestMapping(value = "/products.do", method = RequestMethod.GET)
    public String handleRequest() {
        return "products";
    }

    @GetMapping(path = "/all")
    public List<User> getUsers() throws ValidationException {
        List<User> list = userService.getAllUsers();
        if (list != null) {
            return list;
        } else {
            return null; // ? algun json
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

