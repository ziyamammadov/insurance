package com.azericard.insurance.service;

import com.azericard.insurance.data.UserRepository;
import com.azericard.insurance.entity.LoginMessage;
import com.azericard.insurance.entity.User;
import com.azericard.insurance.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class LoginService {

    private UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginMessage getLoggedUser(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password).orElseThrow(UserNotFoundException::new);
        userRepository.save(user);
        String encodedUsername = new String(Base64.getEncoder().encode(user.getRole().toString().getBytes()));
        return new LoginMessage(username, encodedUsername);
    }
}
