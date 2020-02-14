package com.azericard.insurance.controller;

import com.azericard.insurance.entity.LoginMessage;
import com.azericard.insurance.entity.User;
import com.azericard.insurance.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginMessage checkUser(@RequestBody User user) {
        return loginService.getLoggedUser(user.getUsername(), user.getPassword());
    }
}
