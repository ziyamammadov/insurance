package com.azericard.insurance.service;

import com.azericard.insurance.data.UserRepository;
import com.azericard.insurance.entity.User;
import com.azericard.insurance.exception.GeneralException;
import com.azericard.insurance.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAll() {
        List<User> users = (List<User>) userRepo.findAll();
        if (users.isEmpty()) {
            throw new GeneralException("No Data Found");
        }
        return users;
    }

    public User getOne(long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElseThrow(UserNotFoundException::new);
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    public List<User> getUsersByCompany(long id) {
        List<User> users = (List<User>) userRepo.getUsersByCompanyId(id);
        if (users.isEmpty()) {
            throw new GeneralException("No Data Found");
        }
        return users;
    }
}
