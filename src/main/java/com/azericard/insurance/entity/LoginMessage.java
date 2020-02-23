package com.azericard.insurance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMessage {
    private String username;
    private String authToken;
}
