package com.azericard.insurance.service;

import com.azericard.insurance.data.UserRepository;
import com.azericard.insurance.email.EmailSender;
import com.azericard.insurance.email.EmailStatus;
import com.azericard.insurance.util.EncodedRole;
import com.azericard.insurance.entity.User;
import com.azericard.insurance.exception.AccessNotAllowedException;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final EmailSender emailSender;

    public RegisterService(UserRepository userRepository, EmailSender emailSender) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
    }

    public User save(User user, String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            User saved = userRepository.save(user);
            String message = "http://localhost:8082/user/?id=" + saved.getId() + "&status=true";
            emailSender.send(saved.getEmail(), "Verification Email", message);
            return saved;
        }
        throw new AccessNotAllowedException();
    }

}
