package com.techmatrix18.web.api;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import com.techmatrix18.service.implementation.UserImpl;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserRepository userRepository;
    private UserImpl userService;

    @GetMapping(path = "/")
    public List<User> getUsers() throws ValidationException {
        return userService.getAllUsers();
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

