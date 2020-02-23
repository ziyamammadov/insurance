package com.azericard.insurance.service;

import com.azericard.insurance.data.UserRepository;
import com.azericard.insurance.entity.Role;
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
        List<User> users = userRepo.getUsersByCompany_Id(id);
        if (users.isEmpty()) {
            throw new GeneralException("No Data Found");
        }
        return users;
    }
    public List<User> getAdmins() {
        List<User> users = userRepo.getUsersByRole(Role.ADMIN);
        if (users.isEmpty()) {
            throw new GeneralException("No Data Found");
        }
        return users;
    }

    public List<User> getOperators() {
        List<User> users = userRepo.getUsersByRole(Role.OPERATOR);
        if (users.isEmpty()) {
            throw new GeneralException("No Data Found");
        }
        return users;
    }

    public List<User> getOperatorsByCompany() {
        List<User> users = userRepo.getListOfOperatorsByCompany();
        if (users.isEmpty()) {
            throw new GeneralException("No Data Found");
        }
        return users;
    }

    public User setStatus(long id, String status) {
        User updated = new User();
        updated.setId(id);
        updated.setStatus(Boolean.parseBoolean(status));
        return userRepo.save(updated);

    }
}
