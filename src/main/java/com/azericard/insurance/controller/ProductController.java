package com.azericard.insurance.controller;

import com.azericard.insurance.entity.Company;
import com.azericard.insurance.entity.Insurance;
import com.azericard.insurance.entity.Product;
import com.azericard.insurance.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> get_all(@RequestHeader("authToken") String role) {
        List<Product> all = service.getAll(role);
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get_one(@PathVariable long id, @RequestHeader("authToken") String role) {
        Product product = service.getOne(id, role);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    @PutMapping("/save")
    public ResponseEntity<Product> create(@RequestBody Product product, @RequestHeader("authToken") String role) {
        Product p= service.save(product, role);
        if (p == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Product product, @RequestHeader("authToken") String role) {
        service.delete(product, role);
    }
}
