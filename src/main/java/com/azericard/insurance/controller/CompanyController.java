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
    public List<Company> get_all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Company get_one(@PathVariable long id) {
        return service.getOne(id);
    }

    @PutMapping("/save")
    public Company create(@RequestBody Company company) {
        return service.save(company);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Company company) {
        service.delete(company);
    }
}
