package com.azericard.insurance.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;
    @CreationTimestamp
    private LocalDateTime registerDate;
    private int phoneNumber;
    @UpdateTimestamp
    private LocalDateTime lastLoginDate;
    @Enumerated(EnumType.STRING)
    private Role role;


}
