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
    public User setStatus(@RequestParam("id") long id, @RequestParam("status") String status, @RequestHeader("authToken") String role) {
        return service.setStatus(id, status, role);
    }

    @GetMapping("/all")
    public List<User> get_all(@RequestHeader("authToken") String role) {
        return service.getAll(role);
    }

    @GetMapping("/{id}")
    public User get_one(@PathVariable long id, @RequestHeader("authToken") String role) {
        return service.getOne(id, role);
    }

    @PutMapping("/save")
    public User create(@RequestBody User user, @RequestHeader("authToken") String role) {
        return service.save(user, role);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody User user, @RequestHeader("authToken") String role) {
        service.delete(user, role);
    }

    @GetMapping("/company/{id}")
    public List<User> get_users_by_company(@PathVariable long id, @RequestHeader("authToken") String role) {
        return service.getUsersByCompany(id, role);
    }

    @GetMapping("/admins")
    public List<User> get_admins(@RequestHeader("authToken") String role) {
        return service.getAdmins(role);
    }

    @GetMapping("/operators")
    public List<User> get_operators(@RequestHeader("authToken") String role) {
        return service.getOperators(role);
    }

    @GetMapping("/operatorsByCompany")
    public List<User> get_operators_by_company(@RequestHeader("authToken") String role) {
        return service.getOperatorsByCompany(role);
    }
}
