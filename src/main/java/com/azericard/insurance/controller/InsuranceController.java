package com.azericard.insurance.controller;

import com.azericard.insurance.entity.Insurance;
import com.azericard.insurance.service.InsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    private InsuranceService service;

    public InsuranceController(InsuranceService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Insurance> get_all(@RequestHeader("authToken") String role) {
        return service.getAll(role);
    }

    @GetMapping("/{id}")
    public Insurance get_one(@PathVariable long id, @RequestHeader("authToken") String role) {
        return service.getOne(id, role);
    }

    @PutMapping("/save")
    public Insurance create(@RequestBody Insurance insurance, @RequestHeader("authToken") String role) {
        return service.save(insurance, role);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Insurance insurance, @RequestHeader("authToken") String role) {
        service.delete(insurance, role);
    }
}
