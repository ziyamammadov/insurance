package com.azericard.insurance.controller;

import com.azericard.insurance.entity.Product;
import com.azericard.insurance.service.ProductService;
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
    public List<Product> get_all(@RequestHeader("authToken") String role) {
        return service.getAll(role);
    }

    @GetMapping("/{id}")
    public Product get_one(@PathVariable long id, @RequestHeader("authToken") String role) {
        return service.getOne(id, role);
    }

    @PutMapping("/save")
    public Product create(@RequestBody Product product, @RequestHeader("authToken") String role) {
        return service.save(product, role);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Product product, @RequestHeader("authToken") String role) {
        service.delete(product, role);
    }
}
