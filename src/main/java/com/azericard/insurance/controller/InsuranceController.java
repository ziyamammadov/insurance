package com.azericard.insurance.controller;

import com.azericard.insurance.entity.Client;
import com.azericard.insurance.entity.Company;
import com.azericard.insurance.entity.Insurance;
import com.azericard.insurance.service.InsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Insurance>> get_all(@RequestHeader("authToken") String role) {
        List<Insurance> all = service.getAll(role);
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> get_one(@PathVariable long id, @RequestHeader("authToken") String role) {
        Insurance insurance = service.getOne(id, role);
        if (insurance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(insurance, HttpStatus.FOUND);
    }

    @PutMapping("/save")
    public ResponseEntity<Insurance> create(@RequestBody Insurance insurance, @RequestHeader("authToken") String role) {
        Insurance ins= service.save(insurance, role);
        if (ins == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ins, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Insurance insurance, @RequestHeader("authToken") String role) {
        service.delete(insurance, role);
    }
}
