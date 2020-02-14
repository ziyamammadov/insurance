package com.azericard.insurance.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @CreationTimestamp
    private LocalDateTime registerDate;
    private int phoneNumber;
    @UpdateTimestamp
    private LocalDateTime lastLoginDate;
    @Enumerated(EnumType.STRING)
    private Role role;


}
