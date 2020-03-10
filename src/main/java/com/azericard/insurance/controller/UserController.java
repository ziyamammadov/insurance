package com.azericard.insurance.controller;

import com.azericard.insurance.entity.Company;
import com.azericard.insurance.entity.User;
import com.azericard.insurance.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> get_all(@RequestHeader("authToken") String role) {
        List<User> all = service.getAll(role);
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get_one(@PathVariable long id, @RequestHeader("authToken") String role) {
        User user = service.getOne(id, role);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PutMapping("/save")
    public ResponseEntity<User> create(@RequestBody User user, @RequestHeader("authToken") String role) {
        User u = service.save(user, role);
        if (u == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody User user, @RequestHeader("authToken") String role) {
        service.delete(user, role);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<User>> get_users_by_company(@PathVariable long id, @RequestHeader("authToken") String role) {
        List<User> all = service.getUsersByCompany(id, role);
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<User>> get_admins(@RequestHeader("authToken") String role) {
        List<User> all = service.getAdmins(role);
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @GetMapping("/operators")
    public ResponseEntity<List<User>> get_operators(@RequestHeader("authToken") String role) {
        List<User> all = service.getOperators(role);
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @GetMapping("/operatorsByCompany")
    public ResponseEntity<List<User>> get_operators_by_company(@RequestBody Company company, @RequestHeader("authToken") String role) {
        List<User> all = service.getOperatorsByCompany(company, role);
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
