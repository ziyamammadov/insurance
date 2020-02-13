package com.azericard.insurance.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionEntity {

    private int code;
    private String description;
}
