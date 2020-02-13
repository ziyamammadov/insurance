package com.azericard.insurance.controller;

import com.azericard.insurance.exception.CompanyNotFoundException;
import com.azericard.insurance.exception.ExceptionEntity;
import com.azericard.insurance.exception.GeneralException;
import com.azericard.insurance.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return errors;
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ExceptionEntity handleUserNotFoundException(UserNotFoundException ex) {
        return ExceptionEntity.builder()
                .code(104)
                .description("User not found")
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CompanyNotFoundException.class)
    public ExceptionEntity handleCompanyNotFoundException(CompanyNotFoundException ex) {
        return ExceptionEntity.builder()
                .code(105)
                .description("Company not found")
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(GeneralException.class)
    public ExceptionEntity handleGeneralException(GeneralException ex) {
        return ExceptionEntity.builder()
                .code(101)
                .description(ex.getMessage())
                .build();
    }
}