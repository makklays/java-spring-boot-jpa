package com.techmatrix18.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/v1/positions")
public class PositionController {

    //

    @GetMapping(path = "/")
    public String getPositions() throws ValidationException {
        return "Hello, positions!";
    }
}

