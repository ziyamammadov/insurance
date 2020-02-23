package com.azericard.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Company name may not be blank")
    private String name;
    @NotBlank(message = "Address may not be blank")
    private String address;
    @NotBlank(message = "Mobile number may not be blank")
    private int mobileNumber;
    @NotBlank(message = "Office number may not be blank")
    private int officeNumber;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private String logo;
    @Value(value = "false")
    private boolean status;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<User> users;
}
