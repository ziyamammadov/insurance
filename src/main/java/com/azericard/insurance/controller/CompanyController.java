package com.azericard.insurance.controller;

import com.azericard.insurance.entity.Company;
import com.azericard.insurance.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Company> get_all(@RequestHeader("authToken") String role) {
        return service.getAll(role);
    }

    @GetMapping("/{id}")
    public Company get_one(@PathVariable long id, @RequestHeader("authToken") String role) {
        return service.getOne(id, role);
    }

    @PutMapping("/save")
    public Company create(@RequestBody Company company, @RequestHeader("authToken") String role) {
        return service.save(company, role);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Company company, @RequestHeader("authToken") String role) {
        service.delete(company, role);
    }
}
