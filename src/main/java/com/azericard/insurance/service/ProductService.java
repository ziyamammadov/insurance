package com.azericard.insurance.service;

import com.azericard.insurance.data.ProductRepository;
import com.azericard.insurance.util.EncodedRole;
import com.azericard.insurance.entity.Product;
import com.azericard.insurance.exception.AccessNotAllowedException;
import com.azericard.insurance.exception.GeneralException;
import com.azericard.insurance.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAll(String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            List<Product> products = (List<Product>) productRepo.findAll();
            if (products.isEmpty()) {
                throw new GeneralException("No Data Found");
            }
            return products;
        }
        throw new AccessNotAllowedException();
    }

    public Product getOne(long id, String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            Optional<Product> optionalCompany = productRepo.findById(id);
            return optionalCompany.orElseThrow(ProductNotFoundException::new);
        }
        throw new AccessNotAllowedException();

    }

    public Product save(Product product, String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            return productRepo.save(product);
        }
        throw new AccessNotAllowedException();

    }

    public void delete(Product product, String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            productRepo.delete(product);
        }
        throw new AccessNotAllowedException();
    }
}
