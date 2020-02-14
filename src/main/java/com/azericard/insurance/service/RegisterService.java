package com.azericard.insurance.service;

import com.azericard.insurance.data.UserRepository;
import com.azericard.insurance.email.EmailService;
import com.azericard.insurance.entity.EncodedRole;
import com.azericard.insurance.entity.User;
import com.azericard.insurance.exception.AccessNotAllowedException;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public RegisterService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public User save(User user, String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            User saved = userRepository.save(user);
            String message = "http://localhost:8082/user/?id=" + saved.getId() + "&status=true";
            emailService.send(saved.getEmail(), "Verification Email", message);
            return saved;
        }
        throw new AccessNotAllowedException();
    }

}
