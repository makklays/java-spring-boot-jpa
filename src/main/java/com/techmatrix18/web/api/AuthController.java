package com.techmatrix18.web.api;

import com.techmatrix18.dto.AuthRequest;
import com.techmatrix18.service.impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private final JwtServiceImpl jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(JwtServiceImpl jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(path = "/auth")
    public String AuthenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.JwtToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid User request !");
        }

        //return jwtService.JwtToken(authRequest.getUsername());
    }
}
