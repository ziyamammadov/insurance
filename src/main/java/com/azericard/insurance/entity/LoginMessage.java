package com.azericard.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMessage {
    private String fullName;
    private String authToken;
}
