package com.azericard.insurance.controller;

import com.azericard.insurance.entity.User;
import com.azericard.insurance.service.RegisterService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PutMapping("/signUp")
    public User register(@RequestBody User user, @RequestHeader("authToken") String role) {
        return service.save(user, role);
    }
}
