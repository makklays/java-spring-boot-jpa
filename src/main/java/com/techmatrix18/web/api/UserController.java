package com.techmatrix18.web.api;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserRepository userRepository;

    @GetMapping(path = "/")
    public Iterable<User> getUsers() throws ValidationException {
        return userRepository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addUser (@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam Long positionId) {
        User n = new User();
        n.setFirstname(firstname);
        n.setLastname(lastname);
        n.setEmail(email);
        n.setPositionId(positionId);
        userRepository.save(n);
        return "Saved";
    }
}

