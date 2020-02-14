package com.azericard.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String address;
    private int mobileNumber;
    private int officeNumber;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private String logo;
    private boolean status;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<User> users;
}
