package com.azericard.insurance.entity;

import java.util.Base64;

public class EncodedRole {
    public static final String SUPER_ADMIN = new String(Base64.getEncoder().encode("SUPER_ADMIN".getBytes()));
    public static final String ADMIN = new String(Base64.getEncoder().encode("ADMIN".getBytes()));
    public static final String OPERATOR = new String(Base64.getEncoder().encode("OPERATOR".getBytes()));
}
