package com.azericard.insurance.service;

import com.azericard.insurance.data.UserRepository;
import com.azericard.insurance.entity.EncodedRole;
import com.azericard.insurance.entity.Role;
import com.azericard.insurance.entity.User;
import com.azericard.insurance.exception.AccessNotAllowedException;
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

    public List<User> getAll(String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            List<User> users = (List<User>) userRepo.findAll();
            if (users.isEmpty()) {
                throw new GeneralException("No Data Found");
            }
            return users;
        }
        throw new AccessNotAllowedException();
    }

    public User getOne(long id, String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            Optional<User> optionalUser = userRepo.findById(id);
            return optionalUser.orElseThrow(UserNotFoundException::new);
        }
        throw new AccessNotAllowedException();

    }

    public User save(User user, String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            return userRepo.save(user);
        }
        throw new AccessNotAllowedException();

    }

    public void delete(User user, String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            userRepo.delete(user);
        }
        throw new AccessNotAllowedException();
    }

    public List<User> getUsersByCompany(long id, String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            List<User> users = userRepo.getUsersByCompany_Id(id);
            if (users.isEmpty()) {
                throw new GeneralException("No Data Found");
            }
            return users;
        }
        throw new AccessNotAllowedException();
    }

    public List<User> getAdmins(String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            List<User> users = userRepo.getUsersByRole(Role.ADMIN);
            if (users.isEmpty()) {
                throw new GeneralException("No Data Found");
            }
            return users;
        }
        throw new AccessNotAllowedException();
    }

    public List<User> getOperators(String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            List<User> users = userRepo.getUsersByRole(Role.OPERATOR);
            if (users.isEmpty()) {
                throw new GeneralException("No Data Found");
            }
            return users;
        }
        throw new AccessNotAllowedException();
    }

    public List<User> getOperatorsByCompany(String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            List<User> users = userRepo.getListOfOperatorsByCompany();
            if (users.isEmpty()) {
                throw new GeneralException("No Data Found");
            }
            return users;
        }
        throw new AccessNotAllowedException();
    }

    public User setStatus(long id, String status,String role) {
        User updated = new User();
        updated.setId(id);
        updated.setStatus(Boolean.parseBoolean(status));
        return userRepo.save(updated);

    }
}
