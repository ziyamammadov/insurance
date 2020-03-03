package com.azericard.insurance.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name may not be blank")
    private String name;
    @NotBlank(message = "Surname may not be blank")
    private String surname;
    @NotBlank(message = "Address may not be blank")
    private String address;
    @NotBlank(message = "Mail may not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Not valid mail")
    private String email;
    @NotBlank(message = "Birthday  may not be blank")
    private LocalDate birth;
    @NotBlank(message = "FIN may not be blank")
    private String FIN;
    @NotBlank(message = "Phone number may not be blank")
    private String phoneNumber;
}
