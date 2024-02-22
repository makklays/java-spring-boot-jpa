package com.techmatrix18.web.api;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import com.techmatrix18.service.implementation.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/************************************
 * Author: Alexander Kuziv
 *  Email: makklays@gmail.com
 *   Date: 19-02-2024
 ***********************************/

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserImpl userService;

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
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
    public @ResponseBody String addUser (@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam Long positionId) {
        User u = new User();
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setEmail(email);
        u.setPositionId(positionId);
        userService.addUser(u);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateUser (@RequestParam Long userId, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam Long positionId) {
        User u = userService.getUserById(userId);
        if (!u.getEmail().isEmpty()) {
            u.setFirstname(firstname);
            u.setLastname(lastname);
            u.setEmail(email);
            u.setPositionId(positionId);
            userService.updateUser(u);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{userId:\\\\d+}")
    public @ResponseBody String deleteUser (@PathVariable Long userId) {
        User u = userService.getUserById(userId);
        if (!u.getEmail().isEmpty()) {
            userService.deleteUser(userId);
        }
        return "Deleted";
    }
}

