package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.dto.UserDto;
import com.uca.m2.pdd.Service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationService authenticationService;
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        String token = authenticationService.authenticateUser(userDto.getUsername(), userDto.getPassword());
        return ResponseEntity.ok().body(token);
    }
}

