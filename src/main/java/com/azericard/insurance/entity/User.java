package com.azericard.insurance.entity;

import com.azericard.insurance.util.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name may not be blank")
    private String name;
    @NotBlank(message = "Surname may not be blank")
    private String surname;
    @NotBlank(message = "Username may not be blank")
    private String username;
    @NotBlank(message = "Password may not be blank")
    private String password;
    @NotBlank(message = "Mail may not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Not valid mail")
    private String email;
    @Value(value = "false")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @CreationTimestamp
    private LocalDateTime registerDate;
    @NotNull(message = "Phone number may not be blank")
    private String phoneNumber;
    @UpdateTimestamp
    private LocalDateTime lastLoginDate;
    @Enumerated(EnumType.STRING)
    private Role role;


}
