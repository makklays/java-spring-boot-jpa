package com.techmatrix18.web.api;

import com.techmatrix18.dto.AuthRequest;
import com.techmatrix18.service.impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    JwtServiceImpl jwtService;

    public AuthController(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/auth")
    public String AuthenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return jwtService.JwtToken(authRequest.getUsername());
    }
}

