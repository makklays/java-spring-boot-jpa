package com.techmatrix18.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    //

    @GetMapping(path = "/")
    public String getUsers() throws ValidationException {
        return "Hello, users!";
    }
}

