package com.azericard.insurance.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Product name cannot be blank")
    private String name;
    private long firstAmount;
    private long secondAmount;
    private long thirdAmount;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
