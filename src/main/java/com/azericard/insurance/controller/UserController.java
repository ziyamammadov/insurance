package com.azericard.insurance.controller;

import com.azericard.insurance.entity.User;
import com.azericard.insurance.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping
    public void setStatus(@RequestParam("status") String status){

    }
    @GetMapping("/all")
    public List<User> get_all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User get_one(@PathVariable long id) {
        return service.getOne(id);
    }

    @PutMapping("/save")
    public User create(@RequestBody User user) {
        return service.save(user);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody User user) {
        service.delete(user);
    }
    @GetMapping("/company/{id}")
    public List<User> get_users_by_company(@PathVariable long id) {
        return service.getUsersByCompany(id);
    }

    @GetMapping("/admins")
    public List<User> get_admins() {
        return service.getAdmins();
    }

    @GetMapping("/operators")
    public List<User> get_operators() {
        return service.getOperators();
    }

    @GetMapping("/operatorsByCompany")
    public List<User> get_operators_by_company() {
        return service.getOperatorsByCompany();
    }
}
