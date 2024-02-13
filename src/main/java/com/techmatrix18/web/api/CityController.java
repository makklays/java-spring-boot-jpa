package com.techmatrix18.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    //

    @GetMapping(path = "/")
    public String getCities() throws ValidationException {
        return "Hello, positions!";
    }
}

